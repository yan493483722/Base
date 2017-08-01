package com.yan.basedemo.aty.bar;

import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.toolbar.BaseToolbarUtil;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/7/24.
 * describe：
 * modify:
 * modify date:
 */
public class SlideStatusBarAty extends BaseAty {


    @BindView(R.id.btl_slide_status_bar)
    BaseToolbar btlSlideStatusBar;
    @BindView(R.id.fl_slide_status_bar_main)
    FrameLayout flSlideStatusBarMain;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_slide_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        btlSlideStatusBar.setTitleText("两边侧滑");
        btlSlideStatusBar.setLeftIcon(R.drawable.icon_menu);
        btlSlideStatusBar.setRightIcon(R.drawable.icon_more);
        setBaseToolbar(btlSlideStatusBar, true,flSlideStatusBarMain);

    }

    @Override
    public void initData() {

    }



}