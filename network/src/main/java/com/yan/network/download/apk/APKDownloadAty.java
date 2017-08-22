package com.yan.network.download.apk;

import android.view.View;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.widget.CircleProgress;
import com.yan.network.R;

/**
 * Created by YanZi on 2017/8/22.
 * describe：
 * modify:
 * modify date:
 */
public class APKDownloadAty extends BaseAty implements View.OnClickListener {

    CircleProgress cp_apk_download;

    BaseToolbar btb_apk_download;

    TextView tv_apk_download_left, tv_apk_download_right;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_download_apk);
    }

    @Override
    protected void initView() {
        btb_apk_download = (BaseToolbar) findViewById(R.id.btb_apk_download);
        cp_apk_download = (CircleProgress) findViewById(R.id.cp_apk_download);
        tv_apk_download_left = (TextView) findViewById(R.id.tv_apk_download_left);
        tv_apk_download_right = (TextView) findViewById(R.id.tv_apk_download_right);
        btb_apk_download.setTitleText("正在升级");
        setBaseToolbar(btb_apk_download, false);
        tv_apk_download_left.setOnClickListener(this);
        tv_apk_download_right.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_apk_download_left) {

        } else if (view.getId() == R.id.tv_apk_download_right) {

        }
    }
}
