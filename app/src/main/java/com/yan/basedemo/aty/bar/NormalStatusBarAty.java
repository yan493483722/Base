package com.yan.basedemo.aty.bar;

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
        btbStatusBarNormal.setDefaultLayoutRight(null,"确定");


    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }


}
