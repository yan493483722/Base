package com.yan.basedemo.aty.bar;

import android.content.Intent;
import android.view.View;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YanZi on 2017/7/24.
 * describe：
 * modify:
 * modify date:
 */
public class StatusBarAty extends BaseAty {

    @Override
    protected int setContentLayout() {
        return R.layout.aty_status_bar;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.btn_status_bar_full, R.id.btn_status_bar_normal, R.id.btn_status_bar_normal_img
            , R.id.btn_status_bar_full_img, R.id.btn_status_bar_slide, R.id.btn_status_bar_slide_img
            , R.id.btn_status_bar_fragment, R.id.btn_status_bar_expend, R.id.btn_status_bar_search, R.id.btn_status_bar_more
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_status_bar_full:
                startActivity(new Intent(mAty, FullStatusBarAty.class));
                break;
            case R.id.btn_status_bar_normal:
                startActivity(new Intent(mAty, NormalStatusBarAty.class));
                break;
            case R.id.btn_status_bar_normal_img:
                startActivity(new Intent(mAty, ImgNormalStatusBarAty.class));
                break;
            case R.id.btn_status_bar_full_img:
                startActivity(new Intent(mAty, ImgFullStatusBarAty.class));
                break;
            case R.id.btn_status_bar_slide:
                startActivity(new Intent(mAty, SlideStatusBarAty.class));
                break;
            case R.id.btn_status_bar_slide_img:
                startActivity(new Intent(mAty, SlideImgStatusBarAty.class));
                break;
            case R.id.btn_status_bar_fragment:
                startActivity(new Intent(mAty, MultiStatusBarAty.class));
                break;
            case R.id.btn_status_bar_expend:
                startActivity(new Intent(mAty, StretchableAty.class));
                break;
            case R.id.btn_status_bar_search:
                startActivity(new Intent(mAty, SearchAty.class));
                break;
            case R.id.btn_status_bar_more:
                startActivity(new Intent(mAty, MoreActivity.class));
                break;
            default:
                break;
        }
    }

}
