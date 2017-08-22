package com.yan.basedemo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yan.base.BaseAty;
import com.yan.basedemo.aty.DialogExampleAty;
import com.yan.basedemo.aty.LoginAty;
import com.yan.basedemo.aty.MultiDownloadAty;
import com.yan.basedemo.aty.bar.StatusBarAty;
import com.yan.network.download.apk.APKDownloadAty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainAty extends BaseAty {

    @BindView(R.id.btn_main_dialog)
    Button btnMainDialog;
    @BindView(R.id.btn_main_loading)
    Button btnMainLoading;
    @BindView(R.id.btn_main_permission)
    Button btnMainPermission;
    @BindView(R.id.btn_main_web)
    Button btnMainWeb;
    @BindView(R.id.aty_main)
    LinearLayout atyMain;
    @BindView(R.id.btn_main_status_bar)
    Button btnMainStatusBar;
    @BindView(R.id.btn_main_multi_download)
    Button btnMainMultiDownload;

    private static final int PERMISSION_READ_STORAGE = 10;
    private static final int REQUEST_READ_PHONE_STATE = 11;
    private static final int PERMISSION_OTHERS = 12;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_main);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_STORAGE);
        }
        requestPermission(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        requestPermission(new String[]{Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA}, PERMISSION_OTHERS);
    }

    @OnClick({R.id.btn_main_dialog, R.id.btn_main_loading, R.id.btn_main_permission, R.id.btn_main_web,
            R.id.btn_main_status_bar, R.id.btn_main_net,
            R.id.btn_main_multi_download})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_main_dialog:
                startActivity(new Intent(mAty, DialogExampleAty.class));
//                mSnackBarAndToastManager.showSnackBar("main dialog");
                break;
            case R.id.btn_main_loading:
                mSnackBarAndToastManager.showSnackBar("main loading");
                break;
            case R.id.btn_main_net:
                startActivity(new Intent(mAty, LoginAty.class));
                break;
            case R.id.btn_main_permission:
                mSnackBarAndToastManager.showSnackBar("main permission");
                break;
            case R.id.btn_main_web:
                startActivity(new Intent(mAty, APKDownloadAty.class));
//                mSnackBarAndToastManager.showSnackBar("main web");
                break;
            case R.id.btn_main_status_bar:
                startActivity(new Intent(mAty, StatusBarAty.class));
                break;
            case R.id.btn_main_multi_download:
                startActivity(new Intent(mAty, MultiDownloadAty.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        needCatchKeycodeBack = true;
    }


    @Override
    public void permissionFail(int requestCode) {
        switch (requestCode) {
            case PERMISSION_READ_STORAGE:
                mSnackBarAndToastManager.showSnackBar("读写文件权限没有打开");
                break;
            case REQUEST_READ_PHONE_STATE:
                break;
            case PERMISSION_OTHERS:
                mSnackBarAndToastManager.showSnackBar("请打开相关权限后操作");
                break;
            default:
                break;
        }
    }


    @Override
    public void permissionSuccess(int requestCode) {
//        switch (requestCode) {
//            case PERMISSION_READ_STORAGE:
//                break;
//            case REQUEST_READ_PHONE_STATE:
//                break;
//            case PERMISSION_OTHERS:
//                break;
//            default:
//                break;
//        }
    }

}
