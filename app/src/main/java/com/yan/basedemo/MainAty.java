package com.yan.basedemo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yan.base.BaseAty;
import com.yan.basedemo.aty.DialogExampleAty;
import com.yan.basedemo.aty.LoginAty;
import com.yan.basedemo.aty.MultiDownloadAty;
import com.yan.basedemo.aty.ObserverSimpleAty;
import com.yan.basedemo.aty.bar.StatusBarAty;
import com.yan.basedemo.aty.database.GreenDaoAty;
import com.yan.network.download.apk.APKDownloadAty;

import java.math.BigDecimal;

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
    protected int setContentLayout() {
        return R.layout.aty_main;
    }

    @OnClick({R.id.btn_main_dialog, R.id.btn_main_loading, R.id.btn_main_permission, R.id.btn_main_web,
            R.id.btn_main_status_bar, R.id.btn_main_net,R.id.btn_main_multi_download,R.id.btn_green_dao,
            R.id.btn_observer_simple})
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
            case R.id.btn_green_dao:
                startActivity(new Intent(mAty, GreenDaoAty.class));
                break;
            case R.id.btn_observer_simple:
                startActivity(new Intent(mAty, ObserverSimpleAty.class));
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_STORAGE);
        }
        requestPermission(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        requestPermission(new String[]{Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA}, PERMISSION_OTHERS);
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


    @NonNull
    private String setNumber(CharSequence charSequence, EditText etSubjectMatterTax) {

        int integerSize = 8;
        int decimalSize = 2;

        String tempTax = charSequence.toString();
        //如果不为null且只有一个.
        if (!TextUtils.isEmpty(tempTax)) {
            if (tempTax.startsWith(".")) {//如果以.开头
                tempTax = "0" + tempTax;
            } else {//不以点开头
                if (tempTax.startsWith("0")) {//如果有超过1个以上的连续的0，截取掉前面多余的0
                    if (tempTax.contains(".")) {
                        tempTax = tempTax.replaceAll("^(0+)", "");
                        if (tempTax.startsWith(".")) {
                            tempTax = "0" + tempTax;
                        }
                    } else {
                        tempTax = tempTax.replaceAll("^(0+)", "");
                        if (TextUtils.isEmpty(tempTax)) {
                            tempTax = "0";
                        }
                    }
                }
            }
        }
        //小数点后最多保留两位
        int posDot = tempTax.indexOf(".");
        if (posDot > 0) {
            if (tempTax.length() - posDot - 1 > decimalSize) {
                tempTax = tempTax.substring(0, posDot + decimalSize + 1);
            }
        }

        //防止整数部分大于8位
        int dotPosition = tempTax.indexOf(".");
        if (dotPosition > 0) {//小数
            if (dotPosition > integerSize) {
                tempTax = tempTax.substring(0, integerSize) + tempTax.substring(dotPosition);
            }
        } else {//整数
            if (tempTax.length() > integerSize) {
                tempTax = tempTax.substring(0, integerSize);
            }
        }


        if (!tempTax.equals(charSequence.toString())) {
            etSubjectMatterTax.setText(tempTax);
            etSubjectMatterTax.setSelection(tempTax.length());
        }
        return tempTax;
    }

    String addSum(String tempTax, String tempPrice) {

        try {
            BigDecimal bigDecimal = new BigDecimal(tempTax);
            return bigDecimal.add(new BigDecimal(tempPrice)).toString();
        } catch (Exception e) {
            return null;
        }
    }


}
