package com.yan.base.dialog;

import android.view.LayoutInflater;

import com.yan.base.BaseAty;

/**
 * Created by YanZi on 2017/4/18.
 * describe：
 * modify:
 * modify date:
 */
public class BaseSingleBtnDialog extends BaseDialog {


    public BaseSingleBtnDialog(BaseAty mBaseAty, LayoutInflater mLayoutInflater) {
        super(mBaseAty, mLayoutInflater);
    }


    public void showDiallogSingle(String title, String content) {
        if (dialogView == null) {
            dialogView = initBaseDialog();
        }
        initNormalTitleContent(title, content);
        dialog.setContentView(dialogView);
        dialog.show();
    }


}
