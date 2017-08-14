package com.yan.basedemo.aty.bar.fg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yan.base.BaseFg;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;
import com.yan.basedemo.application.BaseDemoApplication;
import com.yan.basedemo.application.CrashHandler;
import com.yan.basedemo.aty.bar.MultiStatusBarAty;
import com.yan.basedemo.aty.bar.SearchAty;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/8/4.
 * describe：
 * modify:
 * modify date:
 */
public class MultiStatusFindFg extends BaseFg {

    @BindView(R.id.btb_fg_multi_status_find)
    BaseToolbar btbFgMultiStatusFind;

    private int a = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fg_multi_status_find, container, false);
        ButterKnife.bind(this, content);
        setBaseToolbar(btbFgMultiStatusFind, false);
        btbFgMultiStatusFind.setTitleText("发现");
        btbFgMultiStatusFind.setDefaultLayoutRight(null,"发动态");
        btbFgMultiStatusFind.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {

            }

            @Override
            public void clickRight() {
                a -= 10;
                a = 10 / a;
            }
        });
        return content;
    }
}
