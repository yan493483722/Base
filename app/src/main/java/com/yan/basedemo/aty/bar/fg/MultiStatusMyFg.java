package com.yan.basedemo.aty.bar.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yan.base.BaseFg;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/8/4.
 * describe：
 * modify:
 * modify date:
 */
public class MultiStatusMyFg extends BaseFg {

    @BindView(R.id.btb_fg_multi_status_my)
    BaseToolbar btbFgMultiStatusMy;

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        setBaseToolbar(btbFgMultiStatusMy,false);
        btbFgMultiStatusMy.setTitleText("我的");
    }

    @Override
    public int setContentLayout() {
        return R.layout.fg_multi_status_my;
    }

}
