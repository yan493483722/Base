package com.yan.base.toolbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
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
        baseAty.setTheme(R.style.AppTheme);
        switch (toolbar.getBaseToolBarType()) {
            case BaseToolbar.STATUS_BAR_TYPE_NORMAL:
                if (isSlide) {
                    setSlideFullStatus(baseAty);
                    setSlideStatusBar(toolbar, baseAty, true);
                } else {
                    setFullStatus(toolbar, baseAty, true);
                }
                break;
            case BaseToolbar.STATUS_BAR_TYPE_FULL:
                //保持底部
                if (isSlide) {
                    setSlideFullStatus(baseAty);
                    setSlideStatusBar(toolbar, baseAty, false);
                } else {
                    setFullStatus(toolbar, baseAty, false);
                }
                break;
            case BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL:
                if (isSlide) {
                    setImageSlideStatusBar(toolbar, baseAty, true);
                } else {
                    setImageStatusBar(toolbar, baseAty, true);
                }
                break;
            case BaseToolbar.STATUS_BAR_TYPE_IMG_FULL:
                if (isSlide) {
                    setImageSlideStatusBar(toolbar, baseAty, false);
                } else {
                    setImageStatusBar(toolbar, baseAty, false);
                }
                break;
            default:
                break;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setFragmentBaseToolbar(BaseToolbar toolbar, Activity baseAty) {
        baseAty.setTheme(R.style.AppTheme);
        switch (toolbar.getBaseToolBarType()) {
            case BaseToolbar.STATUS_BAR_TYPE_NORMAL:
                setFragmentStatusBar(toolbar, baseAty, true);
                break;
            case BaseToolbar.STATUS_BAR_TYPE_FULL:
                setFragmentStatusBar(toolbar, baseAty, false);
                break;
            case BaseToolbar.STATUS_BAR_TYPE_IMG_NORMAL:
                setFragmentStatusBarImage(toolbar, baseAty, true);
                break;
            case BaseToolbar.STATUS_BAR_TYPE_IMG_FULL:
                setFragmentStatusBarImage(toolbar, baseAty, false);
                break;
            default:
                break;
        }

    }

    private static void setFragmentStatusBar(BaseToolbar toolbar, Activity baseAty, boolean isNormal) {
        View view = new View(baseAty);
        toolbar.addView(view, 0);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(baseAty)));
        if (isNormal) {
            view.setBackgroundColor(Color.HSVToColor(getDarkColor(toolbar.getBackgroundColor())));
        } else {
            view.setBackgroundColor(toolbar.getBackgroundColor());
        }
    }

    private static void setFragmentStatusBarImage(BaseToolbar toolbar, Activity baseAty, boolean isNormal) {
        View view = new View(baseAty);
        toolbar.addView(view, 0);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(baseAty)));
        toolbar.setBackgroundColor(toolbar.getBackgroundColor());
        if (isNormal) {
            view.setBackgroundColor(toolbar.getResources().getColor(R.color.transparent_half));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void setFullStatus(BaseToolbar toolbar, Activity baseAty, boolean isNormal) {
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
        if (isNormal) {
            window.setStatusBarColor(Color.HSVToColor(getDarkColor(toolbar.getBackgroundColor())));
        } else {
            window.setStatusBarColor(toolbar.getBackgroundColor());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void setImageStatusBar(BaseToolbar toolbar, Activity aty, boolean isNormal) {
        Window window = aty.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(isNormal ? aty.getResources().getColor(R.color.transparent_half) : Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
        toolbar.tb_base_tb.setFitsSystemWindows(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void setImageSlideStatusBar(BaseToolbar toolbar, Activity aty, boolean isNormal) {
        Window window = aty.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(isNormal ? aty.getResources().getColor(R.color.transparent_half) : Color.TRANSPARENT);
//        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
        toolbar.setPadding(0, getStatusBarHeight(aty), 0, 0);
        toolbar.tb_base_tb.setFitsSystemWindows(true);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void setSlideFullStatus(Activity baseAty) {
        Window window = baseAty.getWindow();
        //设置decorView 的系统状态栏为可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
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

    }


    public static void setSlideStatusBar(BaseToolbar toolbar, Activity baseAty, boolean isNormal) {
        Window window = baseAty.getWindow();
        toolbar.setFitsSystemWindows(true);
        toolbar.setPadding(0, getStatusBarHeight(baseAty), 0, 0);
        toolbar.setBackgroundColor(toolbar.getBackgroundColor());
        toolbar.setClipToPadding(true);
        if (isNormal) {
            window.setStatusBarColor(baseAty.getResources().getColor(R.color.transparent_half));
        } else {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private static float[] getDarkColor(@ColorInt int bgColor) {
        int red = (bgColor & 0xff0000) >> 16;
        int green = (bgColor & 0x00ff00) >> 8;
        int blue = (bgColor & 0x0000ff);
        int color = Color.rgb(red, green, blue);
        float hsv[] = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = hsv[2] - 0.3f;
        return hsv;
    }

}
