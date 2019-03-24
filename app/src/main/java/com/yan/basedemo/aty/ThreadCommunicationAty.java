package com.yan.basedemo.aty;

import android.os.Handler;
import android.view.View;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;
import com.yan.basedemo.manager.DemoDownloadManager;
import com.yan.basedemo.manager.DownloadEntity;

import java.util.LinkedList;

import butterknife.OnClick;

/**
 * Created by YanZi on 2018/12/26.
 * describe：
 * modify:
 * modify date:
 */
public class ThreadCommunicationAty extends BaseAty {


    @Override
    protected int setContentLayout() {
        return R.layout.aty_thread_communication;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {


    }

    Handler mHandler;

    @OnClick({R.id.btn_thread_communication_start_list, R.id.btn_thread_communication_insert_last, R.id.btn_thread_communication_delete, R.id.btn_thread_communication_stop_and_first})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_thread_communication_start_list:
                DemoDownloadManager.getInstance().startDownload(addData());
                break;
            case R.id.btn_thread_communication_insert_last:
                break;
            case R.id.btn_thread_communication_delete:
                break;
            case R.id.btn_thread_communication_stop_and_first:
                break;
        }
    }


    private LinkedList<DownloadEntity> addData() {
        LinkedList<DownloadEntity> linkedList = new LinkedList<>();
        final int size = linkedList.size();
        for (int i = size; i < size + 5; i++) {
            DownloadEntity downloadEntity1 = new DownloadEntity("任务" + i, 10L);
            linkedList.add(downloadEntity1);
        }
        return linkedList;
    }
}
