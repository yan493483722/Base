package com.yan.basedemo.aty.bar;

import android.os.Bundle;

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
public class NormalStatusBarAty extends BaseAty {


    @BindView(R.id.btb_status_bar_normal)
    BaseToolbar btbStatusBarNormal;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_normal_status_bar);
        ButterKnife.bind(this);
        setBaseToolbar(btbStatusBarNormal,true);
        btbStatusBarNormal.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                mSnackBarAndToastManager.showSnackBar("右侧点击");
            }
        });
        btbStatusBarNormal.setTitleText("正常的标题");
        btbStatusBarNormal.setRightText("确定");
//        btbStatusBarNormal.setBaseToolbarStatusBarType(BaseToolbar.STATUS_BAR_TYPE_NORMAL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }


}
