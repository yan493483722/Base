package com.yan.basedemo.aty.bar;

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
public class SearchAty extends BaseAty {


    @BindView(R.id.btb_search)
    BaseToolbar btbSearch;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_search);
        ButterKnife.bind(this);
        setStatusBar(btbSearch, true);
        btbSearch.setDefaultLayoutRightText("搜索");
        btbSearch.setSearchDefaultLayout(mAty);
        btbSearch.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                mSnackBarAndToastManager.showSnackBar("搜索");
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
