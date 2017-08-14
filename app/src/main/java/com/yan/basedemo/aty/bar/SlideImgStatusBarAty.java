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
public class SlideImgStatusBarAty extends BaseAty {


    @BindView(R.id.btl_slide_status_bar)
    BaseToolbar btlSlideStatusBar;
    @BindView(R.id.fl_slide_status_bar_main)
    FrameLayout flSlideStatusBarMain;
    @BindView(R.id.btn_slide_status_bar_normal)
    Button btnSlideStatusBarNormal;
    @BindView(R.id.nv_slide_status_bar_left)
    NavigationView nvSlideStatusBarLeft;
    @BindView(R.id.nv_slide_status_bar_right)
    NavigationView nvSlideStatusBarRight;
    @BindView(R.id.dl_slide_status_bar)
    DrawerLayout dlSlideStatusBar;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_slide_img_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
//        btlSlideStatusBar.setBaseToolBarType(1);
        if (getIntent() != null && getIntent().getExtras() != null) {

            int type = getIntent().getExtras().getInt("type");
            switch (type) {
                case BaseToolbar.STATUS_BAR_TYPE_NORMAL:
                    btlSlideStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_FULL:
                    btlSlideStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_FULL);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL:
                    btlSlideStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_IMG_FULL:
                    btlSlideStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_IMG_FULL);
                    break;
                default:
                    btlSlideStatusBar.setBaseToolBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
                    break;
            }

        }
        btlSlideStatusBar.setTitleText("两边侧滑");
        btlSlideStatusBar.setToolBarLeftIcon(R.drawable.icon_menu);
        btlSlideStatusBar.setDefaultLayoutRightIcon(R.drawable.icon_more);
        setBaseToolbarSlide(btlSlideStatusBar, true);
    }

    @Override
    public void initData() {
        btlSlideStatusBar.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                if (!dlSlideStatusBar.isDrawerOpen(nvSlideStatusBarLeft)) {
                    dlSlideStatusBar.openDrawer(nvSlideStatusBarLeft);
                }

            }

            @Override
            public void clickRight() {
                if (!dlSlideStatusBar.isDrawerOpen(nvSlideStatusBarRight)) {
                    dlSlideStatusBar.openDrawer(nvSlideStatusBarRight);
                }
            }
        });
    }

    @OnClick({R.id.btn_slide_status_bar_normal})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_slide_status_bar_normal:
                Intent intent = new Intent(mAty, SlideImgStatusBarAty.class);
                intent.putExtra("type", BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL);
                startActivity(intent);
                break;
            default:
                break;

        }
    }


}