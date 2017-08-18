package com.yan.network.download.apk;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import com.yan.base.BaseAty;
import com.yan.base.dialog.BaseThreeMoreBtnDialog;
import com.yan.base.listener.BaseDialogThreeMoreBtnClickListener;
import com.yan.base.uitls.Tools;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by YanZi on 2017/8/17.
 * describe：
 * modify:
 * modify date:
 */
public class APKUpdateManager {

    private static final String TAG = "APKUpdateManager";

    private BaseAty baseAty;

    private LayoutInflater layoutInflater;

    private String title;

    private String content;

    private String url;

    private String saveApkPath;

    ArrayList<String> btnTexts;

    public APKUpdateManager(BaseAty baseAty, String url, String saveApkPath, LayoutInflater layoutInflater) {
        this.baseAty = baseAty;
        this.url = url;
        this.saveApkPath = saveApkPath;
        this.layoutInflater = layoutInflater;
    }

    public void setBtnTexts(ArrayList<String> btnTexts) {
        this.btnTexts = btnTexts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void showUpdateDialog(BaseDialogThreeMoreBtnClickListener baseDialogThreeMoreBtnClickListener) {
        if (btnTexts == null || btnTexts.isEmpty()) {
            Log.e(TAG, "there is something wrong in showUpdateDialog \n please set APKUpdateManager btnTexts first  ==>call method ：setBtnTexts");
            return;
        }
        BaseThreeMoreBtnDialog.Builder builder = new BaseThreeMoreBtnDialog.Builder(baseAty, layoutInflater).
                setBaseDialogThreeMoreBtnClickListener(baseDialogThreeMoreBtnClickListener)
                .setBtnText(btnTexts);
        builder.setContentTextGravity(Gravity.LEFT);
        if (!Tools.isNull(title)) {
            builder.setTitle(title);
        }
        if (!Tools.isNull(content)) {
            builder.setContent(content);
        }
        if (Tools.isNull(title) && Tools.isNull(title)) {
            builder.setTitle("版本更新");
        }
        builder.create().show();
    }

    public void download() {

        if (new File(saveApkPath).exists()) {//存在的处理
            //大小一致。。校验包是否是自己的？？
        }

        APKDownloadPresenter apkDownloadPresenter = new APKDownloadPresenter(baseAty, null);

        apkDownloadPresenter.downLoad(url, saveApkPath);
    }

}
