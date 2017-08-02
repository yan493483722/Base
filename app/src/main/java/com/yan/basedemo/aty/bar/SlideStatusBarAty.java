package com.yan.basedemo.aty.bar;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.btn_slide_status_bar_normal)
    Button btnSlideStatusBarNormal;
    @BindView(R.id.btn_slide_status_bar_img)
    Button btnSlideStatusBarImg;
    @BindView(R.id.nv_slide_status_bar_left)
    NavigationView nvSlideStatusBarLeft;
    @BindView(R.id.nv_slide_status_bar_right)
    NavigationView nvSlideStatusBarRight;
    @BindView(R.id.dl_slide_status_bar)
    DrawerLayout dlSlideStatusBar;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_slide_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
//        btlSlideStatusBar.setBaseToolBarType(1);
        if (getIntent() != null && getIntent().getExtras() != null) {
            btlSlideStatusBar.setBaseToolBarType(getIntent().getExtras().getInt("type"));
        }
        btlSlideStatusBar.setTitleText("两边侧滑");
        btlSlideStatusBar.setLeftIcon(R.drawable.icon_menu);
        btlSlideStatusBar.setRightIcon(R.drawable.icon_more);
        setSlideBaseToolbar(btlSlideStatusBar, true);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_slide_status_bar_normal, R.id.btn_slide_status_bar_img})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_slide_status_bar_normal:
                Intent intent = new Intent(mAty, SlideStatusBarAty.class);
                intent.putExtra("type", BaseToolbar.STATUS_BAR_TYPE_NORMAL);
                startActivity(intent);
                break;
            case R.id.btn_slide_status_bar_img:
                break;
            default:
                break;

        }
    }


}