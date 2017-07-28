package com.yan.basedemo.aty.bar;

import android.os.Bundle;
import android.widget.ImageView;

import com.yan.base.BaseAty;
import com.yan.base.widget.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/7/24.
 * describe：
 * modify:
 * modify date:
 */
public class ImgStatusBarAty extends BaseAty {
    @BindView(R.id.btb_status_bar_img)
    BaseToolbar btbStatusBarImg;
    @BindView(R.id.img_status)
    ImageView imgStatus;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_img_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        setBaseToolbar(btbStatusBarImg, true);
        btbStatusBarImg.setTitleText("全屏的状态栏");
        btbStatusBarImg.setRightText("全屏");

        btbStatusBarImg.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {

            }
        });
    }

    @Override
    public void initData() {

    }


}
