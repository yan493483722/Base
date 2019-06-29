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
public class MultiStatusFindFg extends BaseFg {

    @BindView(R.id.btb_fg_multi_status_find)
    BaseToolbar btbFgMultiStatusFind;

    private int a = 10;

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        setBaseToolbar(btbFgMultiStatusFind, false);
        btbFgMultiStatusFind.setTitleText("发现");
        btbFgMultiStatusFind.setDefaultLayoutRight(null,"发动态");
        btbFgMultiStatusFind.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft(int type) {

            }

            @Override
            public void clickRight(int type) {
                a -= 10;
                a = 10 / a;
            }
        });
    }

    @Override
    public int setContentLayout() {
        return R.layout.fg_multi_status_find;
    }


}
