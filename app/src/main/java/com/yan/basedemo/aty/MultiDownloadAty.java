package com.yan.basedemo.aty;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/7/20.
 * describe：
 * modify:
 * modify date:
 */
public class MultiDownloadAty extends BaseAty {

    @BindView(R.id.tb_multi_download)
    Toolbar tbMultiDownload;
    @BindView(R.id.rc_multi_download)
    RecyclerView rcMultiDownload;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_multi_download);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(tbMultiDownload);
        tbMultiDownload.setTitle("multi download");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tbMultiDownload.setElevation(5);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            tbMultiDownload.setBackgroundColor(getColor(R.color.colorPrimary));
//        }else{
//            tbMultiDownload.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        }
    }

    @Override
    public void initData() {

    }
}
