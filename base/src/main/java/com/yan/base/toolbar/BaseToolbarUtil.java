package com.yan.base.toolbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/7/31.
 * describe：
 * modify:
 * modify date:
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BaseToolbarUtil {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setBaseToolbar(BaseToolbar toolbar, Activity baseAty, boolean isSlide) {
        switch (toolbar.getBaseToolBarType()) {
            case BaseToolbar.STATUS_BAR_TYPE_NORMAL:
                baseAty.setTheme(R.style.AppTheme);
                if (isSlide) {
                    setFullStatus(toolbar, baseAty);
                    setSlideStatusBarNormal(toolbar, baseAty);
                }
                break;
            case BaseToolbar.STATUS_BAR_TYPE_FULL:
                //保持底部
                baseAty.setTheme(R.style.AppThemeFull);
                setFullStatus(toolbar, baseAty);
                if (isSlide) {
                    setSlideStatusBarFull(toolbar, baseAty);
                }
                break;
            case BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL:
                baseAty.setTheme(R.style.AppTheme);
                translucentStatusBar(baseAty, false);
                toolbar.tb_base_tb.setFitsSystemWindows(true);
                break;
            case BaseToolbar.STATUS_BAR_TYPE_IMG_FULL:
                baseAty.setTheme(R.style.AppTheme);
                translucentStatusBar(baseAty, true);
                toolbar.tb_base_tb.setFitsSystemWindows(true);
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void setFullStatus(BaseToolbar toolbar, Activity baseAty) {
        Window window = baseAty.getWindow();
        //设置decorView 的系统状态栏为可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //设置 toolbar 自适应
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //设置 设置窗体自适应 自适应
            ViewCompat.setFitsSystemWindows(mChildView, false);
            //设置requestFitSystemWindows后请求一个新的调度
            ViewCompat.requestApplyInsets(mChildView);//是否需要调用？嵌套了?
        }
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(toolbar.getBackgroundColor());
    }


    /**
     * translucentStatusBar(full-screen)
     * <p>
     * 1. set Flags to full-screen
     * 2. set FitsSystemWindows to false
     *
     * @param hideStatusBarBackground hide statusBar's shadow
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void translucentStatusBar(Activity aty, boolean hideStatusBarBackground) {
        Window window = aty.getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

    /**
     * return statusBar's Height in pixels
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

    public static void setSlideStatusBarNormal(BaseToolbar toolbar, Activity baseAty) {
        Window window = baseAty.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        toolbar.setFitsSystemWindows(true);
        toolbar.setPadding(0, getStatusBarHeight(baseAty), 0, 0);
        toolbar.setClipToPadding(true);
    }

    public static void setSlideStatusBarFull(BaseToolbar toolbar, Activity baseAty) {
        Window window = baseAty.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        toolbar.setFitsSystemWindows(true);
        toolbar.setPadding(0, getStatusBarHeight(baseAty), 0, 0);
        toolbar.setBackgroundColor(toolbar.getBackgroundColor());
        toolbar.setClipToPadding(true);
    }

}
