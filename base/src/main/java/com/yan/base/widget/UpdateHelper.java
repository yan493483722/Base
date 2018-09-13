package com.yan.base.widget;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.utils.IOUtils;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.yan.base.BuildConfig;
import com.yan.base.R;
import com.yan.base.application.AppManager;

import java.io.File;

import cn.earthyan.dialogandpop.dialog.BaseSingleBtnDialog;
import cn.earthyan.dialogandpop.listener.BaseDialogSingleBtnClickListener;


/**
 * Created by YanZi on 2018/6/28.
 * describe：
 * modify:
 * modify date:
 */
public class UpdateHelper {

    private Activity mContext;
    private String url;
    private Resources mResources;
    LayoutInflater mLayoutInflater;
    String versionName;

    MaterialDialog materialDialog;

    DownloadTask task;

    /* 下载包安装路径 */
    private String savePath = Environment.getExternalStorageDirectory() + File.separator + BuildConfig.APPLICATION_ID + File.separator;
    ;
    private String saveFileName;
    private String finalAPKFile;

    private UpdateHelper() {

    }

    public UpdateHelper(Activity mContext, Resources mResources, LayoutInflater mLayoutInflater, String versionName, String url) {
        this.versionName = versionName;
        saveFileName = versionName;
        this.mResources = mResources;
        this.mLayoutInflater = mLayoutInflater;
        this.mContext = mContext;
        this.url = url;
        finalAPKFile = savePath + saveFileName;
    }

    public void startUpdate() {
        //存在目录 也有可能存在文件
        File mFile = new File(finalAPKFile);
        if (mFile.exists()) {
            mFile.delete();
        }
        IOUtils.createFolder(savePath);
        OkDownload.getInstance().setFolder(savePath);
        GetRequest<File> request = OkGo.get(url);//
        if (OkDownload.getInstance().hasTask(versionName)) {
            OkDownload.getInstance().getTask(versionName).remove(true);
        }
        task = OkDownload.request(versionName, request);
        task.fileName(saveFileName);
        task.folder(OkDownload.getInstance().getFolder());
        task.save().register(mDownloadListener);
        task.start();
    }

    private DownloadListener mDownloadListener = new DownloadListener(versionName) {
        @Override
        public void onStart(Progress progress) {
            materialDialog = new MaterialDialog.Builder(mContext)
                    .title(String.format(mResources.getString(R.string.app_update), mResources.getString(R.string.app_name)))
                    .content(mResources.getString(R.string.app_downloading_now))
                    .progress(false, 100, false)
                    .canceledOnTouchOutside(false)
                    .cancelable(false).show();
        }

        @Override
        public void onProgress(Progress progress) {
            materialDialog.setProgress((int) (progress.currentSize * 100 / progress.totalSize));
        }

        @Override
        public void onError(Progress progress) {
            task.remove(true);
            new BaseSingleBtnDialog.Builder(mContext, mLayoutInflater)
                    .setBaseDialogSingleBtnClickListener(new BaseDialogSingleBtnClickListener() {
                        @Override
                        public void clickBtn(int tag) {
                            AppManager.getAppManager().AppExit(mContext);
                        }
                    })
                    .setTag(0).setContent(mResources.getString(R.string.server_error)).
                    create().show();
        }

        @Override
        public void onFinish(File file, Progress progress) {
            materialDialog.setProgress((int) (progress.currentSize * 100 / progress.totalSize));
            installAPK(file);
        }

        @Override
        public void onRemove(Progress progress) {

        }
    };

    private void installAPK(File apkFile) {
        if (!apkFile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileprovider", apkFile);
            i.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            i.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(i);
    }

}
