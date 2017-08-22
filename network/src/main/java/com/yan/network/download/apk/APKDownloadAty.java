package com.yan.network.download.apk;

import com.yan.base.BaseAty;
import com.yan.base.widget.CircleProgress;
import com.yan.network.R;

/**
 * Created by YanZi on 2017/8/22.
 * describe：
 * modify:
 * modify date:
 */
public class APKDownloadAty extends BaseAty {

    CircleProgress cp_apk_download;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_download_apk);
    }

    @Override
    protected void initView() {
        cp_apk_download= (CircleProgress) findViewById(R.id.cp_apk_download);
    }

    @Override
    public void initData() {

    }
}
