package com.yan.basedemo.aty;

import android.support.v7.widget.RecyclerView;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/7/20.
 * describe：
 * modify:
 * modify date:
 */
public class MultiDownloadAty extends BaseAty {


    @BindView(R.id.btb_multi_download)
    BaseToolbar btbMultiDownload;
//    @BindView(R.id.rc_multi_download)
//    RecyclerView rcMultiDownload;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_multi_download);
        ButterKnife.bind(this);

        btbMultiDownload.setTitleText("列表下载");

        btbMultiDownload.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
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
    protected void initView() {

    }

    @Override
    public void initData() {

    }

}
