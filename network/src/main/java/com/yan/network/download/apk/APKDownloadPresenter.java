package com.yan.network.download.apk;

import android.app.ProgressDialog;
import android.util.Log;

import com.yan.base.BaseAty;
import com.yan.mvp.BasePresenter;
import com.yan.mvp.BaseViewer;
import com.yan.network.download.DownloadEntity;
import com.yan.network.download.DownloadProgressListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YanZi on 2017/8/17.
 * describe：
 * modify:
 * modify date:
 */
public class APKDownloadPresenter extends BasePresenter {

    public APKDownloadPresenter(BaseAty baseAty, BaseViewer baseViewer) {
        super(baseAty, baseViewer);
    }

    //下载进度
    private ProgressDialog mypDialog;

    void downLoad(String url, final String saveApkPath, final String fileName) {

        //开始下载
        mypDialog = new ProgressDialog(baseAty);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置进度条风格，风格为长形，有刻度的
        mypDialog.setTitle("");
        mypDialog.setMessage("努力下载中");
        mypDialog.setProgress(0);
        mypDialog.setCancelable(false);
        mypDialog.setProgressNumberFormat("%1d kb/ %2d kb");
        mypDialog.show();


        HttpLoggingInterceptor httpLoggingInterceptor;
//        if () {
//        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
//        } else {
        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(
                        new DownloadProgressResponseBody(originalResponse.body(), new DownloadProgressListener() {

                            @Override
                            public void onStart(long totalLength) {

                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onDownloading(long totalBytesRead, long totalLength) {
                                Log.e("yan", "totalBytesRead=" + totalBytesRead + " totalLength=" + totalLength);
                            }

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void netError(Object tag) {

                            }

                            @Override
                            public void serviceError(Call call) {

                            }
                        }))
                        .build();
            }
        }).addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

//        mOkHttpClient.networkInterceptors().add();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.118:8010/")
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //获取接口实例
        DownloadService downloadService = retrofit.create(DownloadService.class);

        Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlAsync(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("yan", "this is the down load " + response);
                if (response.code() == 200) {
                    writeStreamToFile(response.body(), saveApkPath, fileName);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    private boolean writeStreamToFile(ResponseBody body, String saveApkPath, String fileName) {
        try {
            File dir = new File(saveApkPath);
            if (!dir.isDirectory()) {//存在的处理
                dir.mkdirs();
            }
            File apkFile = new File(saveApkPath, fileName);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(apkFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                if (fileSizeDownloaded == fileSize) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
