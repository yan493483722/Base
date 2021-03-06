package com.yan.basedemo.aty.bar;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.toolbar.DefaultSearchToolbar;
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


    @BindView(R.id.dst_search)
    DefaultSearchToolbar dstSearch;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_search;
    }

    @Override
    protected void initView() {
        setBaseToolbar(dstSearch, true);
        dstSearch.setDefaultLayoutRight(null, "搜索");
//        btbSearch.setSearchDefaultLayout(mAty);
        dstSearch.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft(int type) {
                onBackPressed();
            }

            @Override
            public void clickRight(int type) {
                mSnackBarAndToastManager.showSnackBar("搜索");
            }
        });
    }

    @Override
    public void initData() {

    }

}
