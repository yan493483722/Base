package com.yan.basedemo.aty.bar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

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
public class FullStatusBarAty extends BaseAty {
    @BindView(R.id.btb_status_bar_full)
    BaseToolbar btbStatusBarFull;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_full_status_bar);
        ButterKnife.bind(this);

    }

    @Override
    protected void initView() {
        setBaseToolbar(btbStatusBarFull,true);
        btbStatusBarFull.setTitleText("全屏的状态栏");
        btbStatusBarFull.setRightText("全屏");

        btbStatusBarFull.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {

            }
        });
        //        btbStatusBarNormal.setBaseToolbarStatusBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
    }

    @Override
    public void initData() {

    }


}
