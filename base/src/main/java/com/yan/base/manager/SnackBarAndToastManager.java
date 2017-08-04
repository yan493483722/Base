package com.yan.base.manager;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.yan.base.BaseAty;

/**
 * Created by YanZi on 2017/4/13.
 * describe：
 * modify:
 * modify date:
 */
public class SnackBarAndToastManager {

    BaseAty baseAty;

    public SnackBarAndToastManager(  BaseAty baseAty) {
        this.baseAty =baseAty;
    }

    /**
     * 显示SnackBar
     *
     * @param text
     */
    public void showSnackBar(String text) {
        if (baseAty==null||baseAty.isFinishing()) {
            return;
        }
        Snackbar.make(baseAty.getWindow().getDecorView(), text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示SnackBar
     *
     * @param view
     * @param text
     */
    public void showSnackBar(String text ,View view ) {
        if (baseAty==null||baseAty.isFinishing()||view==null) {
            return;
        }
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     *
     * @param text
     */
    public void showToast(String text) {
        if (baseAty==null||baseAty.isFinishing()) {
            return;
        }
        Toast.makeText(baseAty, text, Toast.LENGTH_SHORT).show();
    }

}
