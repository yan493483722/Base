package com.yan.network.download;

/**
 * Created by YanZi on 2017/8/18.
 * describe：
 * modify:
 * modify date:
 */
public interface DownloadProgressListener {

    void update(long totalBytesRead, long totalLength, boolean complete);
}
