package com.yan.basedemo.manager;

import java.util.LinkedList;

/**
 * Created by YanZi on 2018/12/26.
 * describe：
 * modify:
 * modify date:
 */
public class DemoDownloadManager implements SingleTaskDownloadThread.SingleTaskDownloadCallBack {


    /**
     * 下载队列维护线程 每隔一段时间 在列表中取出一个变量
     */
    Thread downloadListThread;

    /**
     * 新任务加入线程，有新的任务进行加入下载队列
     */
    Thread addTaskThread;

    /**
     * 新任务优先下载线程 暂停当前正在进行的下载进度，优先开始要下载的下载内容
     */
    Thread moveTaskFirstThread;

    private volatile static DemoDownloadManager mInstance;

    private LinkedList<DownloadEntity> linkedList;

    private DemoDownloadManager() {
        printListData();
    }

    public static DemoDownloadManager getInstance() {
        if (mInstance == null) {
            synchronized (DemoDownloadManager.class) {
                if (mInstance == null) {
                    mInstance = new DemoDownloadManager();
                }
            }
        }
        return mInstance;
    }

    public void startDownload(LinkedList<DownloadEntity> entities) {
        if (downloadListThread != null) {
            linkedList.addAll(entities);
        } else {
            downloadListThread = new Thread() {
                LinkedList<SingleTaskDownloadThread> threadLocal;

                @Override
                public void run() {
                    // start 5 task single time
                    if (!linkedList.isEmpty()) {
                        if (threadLocal.size() < 5) {
                            threadLocal.add(new SingleTaskDownloadThread(linkedList.getFirst(), DemoDownloadManager.this));
                        } else {
                            // judge downLoadListThread have how many child and add to start

                        }
                    } else {
                        //release the resource and stop zhe down
                        downloadListThread.interrupt();
                    }
                }
            };
        }
    }


    private void initDownloadThread() {
        if (downloadListThread != null) {
            downloadListThread = new Thread() {
                @Override
                public void run() {
                    printListData();

                }
            };
        }
    }


    private void printListData() {
        System.out.println("===================剩余的列表实体 start ==================");
        for (DownloadEntity downloadEntity : linkedList) {
            System.out.println(downloadEntity.toString());
        }
        System.out.println("===================剩余的列表实体 end ==================");
    }

    @Override
    public void success(DownloadEntity downloadEntity) {

    }

    @Override
    public void fail(DownloadEntity downloadEntity) {

    }
}
