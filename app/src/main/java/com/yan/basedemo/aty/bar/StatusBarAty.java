package com.yan.basedemo.aty.bar;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yan.base.BaseAty;
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
public class StatusBarAty extends BaseAty {
    @BindView(R.id.btn_status_bar_full)
    Button btnStatusBarFull;
    @BindView(R.id.btn_status_bar_normal)
    Button btnStatusBarNormal;
    @BindView(R.id.btn_status_bar_img)
    Button btnStatusBarImg;
    @BindView(R.id.btn_status_bar_no)
    Button btnStatusBarNo;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_status_bar_full, R.id.btn_status_bar_normal, R.id.btn_status_bar_img, R.id.btn_status_bar_no})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_status_bar_full:
                startActivity(new Intent(mAty,FullStatusBarAty.class));
                break;
            case R.id.btn_status_bar_normal:
                startActivity(new Intent(mAty,NormalStatusBarAty.class));
                break;
            case R.id.btn_status_bar_img:
                startActivity(new Intent(mAty,ImgStatusBarAty.class));
                break;
            case R.id.btn_status_bar_no:
                startActivity(new Intent(mAty,NoStatusBarAty.class));
                break;
            default:
                break;
        }
    }

}
