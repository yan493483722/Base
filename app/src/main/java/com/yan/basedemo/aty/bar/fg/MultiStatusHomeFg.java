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
public class MultiStatusHomeFg extends BaseFg {

    @BindView(R.id.btb_fg_multi_status_home)
    BaseToolbar btbFgMultiStatusHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fg_multi_status_home, container, false);
        ButterKnife.bind(this,content);
        setBaseToolbar(btbFgMultiStatusHome,false);
        btbFgMultiStatusHome.setTitleText("主页");
        return content;
    }
}
