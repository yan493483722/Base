package com.yan.base.manager;

import android.app.ProgressDialog;
import android.text.TextUtils;

import com.yan.base.BaseAty;

/**
 * Created by YanZi on 2017/4/13.
 * describe：
 * modify:
 * modify date:
 */
public class ProgressDialogManager {

   public ProgressDialog mSystemProgressDialog;

    private BaseAty baseAty;


    public ProgressDialogManager(BaseAty baseAty) {
        this.baseAty = baseAty;
    }

    /**
     * 显示系统类型的加载框
     */
    public void showSystemLaoding(String content) {
        if (null == mSystemProgressDialog) {
            mSystemProgressDialog = new ProgressDialog(baseAty);
        }
        if (!TextUtils.isEmpty(content)) {
            mSystemProgressDialog.setMessage(content);
        } else {
            mSystemProgressDialog.setMessage("loading now");
        }
        if (!baseAty.isFinishing()) {
            mSystemProgressDialog.show();
        }
    }

    /**
     * 取消系统类型的加载框
     */
    public void cancelSystemLoading() {
        if (null != mSystemProgressDialog) {
            mSystemProgressDialog.dismiss();
        }
    }

    /**
     * 显示自定义类型的加载框
     */
    public void showLoading(String content) {

    }

    /**
     * 取消系统类型的加载框
     */
    public void cancleLoading() {
        if (null != mSystemProgressDialog) {
            mSystemProgressDialog.dismiss();
        }
    }

}
