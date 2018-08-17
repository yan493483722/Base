package com.yan.network.download.apk;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import com.yan.base.BaseAty;
import com.yan.base.uitls.Tools;

import java.util.ArrayList;

import cn.earthyan.dialogandpop.dialog.BaseThreeMoreBtnDialog;
import cn.earthyan.dialogandpop.listener.BaseDialogThreeMoreBtnClickListener;

/**
 * Created by YanZi on 2017/8/17.
 * describe：
 * 下载可能出现的bug
 * http://blog.csdn.net/qqyanjiang/article/details/51076940
 * http://blog.csdn.net/liwujun11111/article/details/52170337
 * <p>
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

    private String saveFilePath;
    private String fileName;

    ArrayList<String> btnTexts;

    public APKUpdateManager(BaseAty baseAty, String url, String saveFilePath, String fileName, LayoutInflater layoutInflater) {
        this.baseAty = baseAty;
        this.url = url;
        this.saveFilePath = saveFilePath;
        this.fileName = fileName;
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


//        NotificationManager  notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_download)
//                .setContentTitle("Download")
//                .setContentText("Downloading File")
//                .setAutoCancel(true);
//
//        notificationManager.notify(0, notificationBuilder.build());

        APKDownloadPresenter apkDownloadPresenter = new APKDownloadPresenter(baseAty, null);

        apkDownloadPresenter.downLoad(url, saveFilePath, fileName);
    }

}
