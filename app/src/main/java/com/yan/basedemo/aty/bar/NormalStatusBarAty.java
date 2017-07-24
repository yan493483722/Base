package com.yan.basedemo.aty.bar;

import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.yan.base.BaseAty;
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
    @BindView(R.id.tb_normal_status_bar)
    Toolbar tbNormalStatusBar;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_normal_status_bar);
        ButterKnife.bind(this);
        setSupportActionBar(tbNormalStatusBar);
        tbNormalStatusBar.setNavigationIcon(R.mipmap.icon_back);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tbNormalStatusBar.setElevation(5);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tbNormalStatusBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
