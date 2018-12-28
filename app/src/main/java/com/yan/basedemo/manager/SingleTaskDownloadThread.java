package com.yan.basedemo.manager;

import java.util.Random;

/**
 * Created by YanZi on 2018/12/28.
 * describe：
 * modify:
 * modify date:
 */
public class SingleTaskDownloadThread extends Thread {

    /**
     * 下载的实体类
     */
    DownloadEntity downloadEntity;

    /**
     *
     */
    SingleTaskDownloadCallBack singleTaskDownloadCallBack;


    private SingleTaskDownloadThread() {

    }

    public SingleTaskDownloadThread(DownloadEntity downloadEntity, SingleTaskDownloadCallBack singleTaskDownloadCallBack) {
        this.downloadEntity = downloadEntity;
        this.singleTaskDownloadCallBack = singleTaskDownloadCallBack;
    }

    @Override
    public void run() {
        System.out.println(" 准备开始下载任务 " + downloadEntity.getTaskName());
        try {
            Thread.sleep(downloadEntity.getTaskTime().byteValue() * 10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" 下载任务结束 " + downloadEntity.getTaskName());
        if (singleTaskDownloadCallBack != null) {
            if (new Random().nextBoolean()) {
                singleTaskDownloadCallBack.success(downloadEntity);
            } else {
                singleTaskDownloadCallBack.fail(downloadEntity);
            }
        }
    }

    /**
     * 单个任务下载回调
     */
    interface SingleTaskDownloadCallBack {

        void success(DownloadEntity downloadEntity);

        void fail(DownloadEntity downloadEntity);
    }
}
