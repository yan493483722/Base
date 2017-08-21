package com.yan.network.download.apk;

import android.util.Log;

import com.yan.network.download.DownloadProgressListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by YanZi on 2017/8/18.
 * describe：
 * modify:
 * modify date:
 */
public class DownloadProgressResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private DownloadProgressListener progressListener;
    private BufferedSource bufferedSource;

    public DownloadProgressResponseBody(ResponseBody responseBody,
                                        DownloadProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;

                if (null != progressListener) {
                    if (bytesRead == -1) {
                        progressListener.onCompleted();
                    } else {
                        if(bytesRead==byteCount){
                            progressListener.onStart(responseBody.contentLength());
                        }else{
                            progressListener.onDownloading(totalBytesRead, responseBody.contentLength());
                        }
                    }

                }
                return bytesRead;
            }
        };

    }
}
