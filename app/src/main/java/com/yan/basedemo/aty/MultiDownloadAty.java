package com.yan.basedemo.aty;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;

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
    protected int setContentLayout() {
        return R.layout.aty_multi_download;
    }

    @Override
    protected void initView() {
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
    public void initData() {

    }

}
