package com.yan.basedemo.aty;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.OnClick;

/**
 * Created by YanZi on 2018/12/26.
 * describe：
 * modify:
 * modify date:
 */
public class ThreadCommunicationAty extends BaseAty {


    /**
     * 下载线程  每个一段时间，将对应的变量消化掉
     */
    Thread downLoadThread;

    /**
     * 下载队列维护线程 每隔一段时间 在列表中取出一个变量
     */
    Thread downLoadListThread;

    /**
     * 新任务加入线程，有新的任务进行加入下载队列
     */
    Thread addTaskThread;

    /**
     * 新任务优先下载线程 暂停当前正在进行的下载进度，优先开始要下载的下载内容
     */
    Thread moveTaskFirstThread;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_thread_communication;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        downLoadThread = new Thread(new Runnable() {
            boolean isOver;
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        //开始做自己的工作了
                        if (msg.what == 1) {

                            // 开始做你的工作了
                        } else if (msg.what == 2) {
                            System.out.println(Thread.currentThread().getName() + "  插入了新工作");
                        }
                    }
                };
                for (; ; ) {
                    if (isOver){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "  do some routine work here ");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Looper.loop();
            }
        });
        downLoadThread.start();
    }

    Handler mHandler;

    @OnClick({R.id.btn_thread_communication_start_list, R.id.btn_thread_communication_insert_last, R.id.btn_thread_communication_delete, R.id.btn_thread_communication_stop_and_first})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_thread_communication_start_list:
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessageAtFrontOfQueue(message);
                break;
            case R.id.btn_thread_communication_insert_last:
                break;
            case R.id.btn_thread_communication_delete:
                break;
            case R.id.btn_thread_communication_stop_and_first:
                break;
        }
    }

}
