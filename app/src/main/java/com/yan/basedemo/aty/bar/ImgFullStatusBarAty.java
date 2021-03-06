package com.yan.basedemo.aty.bar;

import android.widget.ImageView;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/7/24.
 * describe：
 * modify:
 * modify date:
 */
public class ImgFullStatusBarAty extends BaseAty {
    @BindView(R.id.btb_status_bar_img)
    BaseToolbar btbStatusBarImg;
    @BindView(R.id.img_status)
    ImageView imgStatus;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_img_full_status_bar;
    }

    @Override
    protected void initView() {
        setBaseToolbar(btbStatusBarImg, true);
        btbStatusBarImg.setTitleText("图片的状态栏");
        btbStatusBarImg.setDefaultLayoutRight(null,"图片");

        btbStatusBarImg.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft(int type) {
                onBackPressed();
            }

            @Override
            public void clickRight(int type) {

            }
        });
    }

    @Override
    public void initData() {

    }


}
