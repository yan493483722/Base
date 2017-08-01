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


    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_multi_download);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }
}
