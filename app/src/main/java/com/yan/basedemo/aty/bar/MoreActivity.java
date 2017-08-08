package com.yan.basedemo.aty.bar;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/8/8.
 * describe：
 * modify:
 * modify date:
 */
public class MoreActivity extends BaseAty {
    @BindView(R.id.btb_more)
    BaseToolbar btbMore;


    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_more);
        ButterKnife.bind(this);

        btbMore.setTitleText("更多");
        btbMore.tb_base_tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        btbMore.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {

            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

}
