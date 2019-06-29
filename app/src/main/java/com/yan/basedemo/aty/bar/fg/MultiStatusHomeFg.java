package com.yan.basedemo.aty.bar.fg;

import android.view.View;

import com.yan.base.BaseFg;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;

/**
 * Created by YanZi on 2017/8/4.
 * describe：
 * modify:
 * modify date:
 */
public class MultiStatusHomeFg extends BaseFg {

    @BindView(R.id.btb_fg_multi_status_home)
    BaseToolbar btbFgMultiStatusHome;

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        setBaseToolbar(btbFgMultiStatusHome,false);
        btbFgMultiStatusHome.setTitleText("主页");
    }

    @Override
    public int setContentLayout() {
        return R.layout.fg_multi_status_home;
    }

}
