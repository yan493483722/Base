package com.yan.network.download;

import com.yan.mvp.BaseViewer;

/**
 * Created by YanZi on 2017/8/18.
 * describe：
 * modify:
 * modify date:
 */
public interface DownloadProgressListener extends BaseViewer{

    //开始下载
    void onStart(long totalLength);
    //取消下载
    void onCancel();
    //下载中
    void onDownloading(long totalBytesRead, long totalLength);
    //下载完成
    void onCompleted();


}
