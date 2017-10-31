package com.yan.basedemo.aty;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yan.base.BaseAty;
import com.yan.base.pop.SelectPhotoPop;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2017/10/17.
 * describe：
 * modify:
 * modify date:
 */
public class UploadAty extends BaseAty {


    @BindView(R.id.btb_upload)
    BaseToolbar btbUpload;
    @BindView(R.id.iv_id_front)
    ImageView ivIdFront;
    @BindView(R.id.iv_id_back)
    ImageView ivIdBack;
    @BindView(R.id.iv_bank_front)
    ImageView ivBankFront;
    @BindView(R.id.iv_bank_back)
    ImageView ivBankBack;
    @BindView(R.id.ll_submit)
    LinearLayout llSubmit;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;


    String uid;

     SelectPhotoPop selectPhotoPop;
//    private TakePhoto takePhoto;
//    InvokeParam invokeParam;
    float totalHeight = 0;

    //保存到本地的图的Uri
    Uri idUirZheng;
    Uri idUirFan;
    Uri bankUirZheng;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_upload);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        btbUpload.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
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
