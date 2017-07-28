package com.yan.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yan.base.application.AppManager;
import com.yan.base.application.GlobalPreference;
import com.yan.base.listener.PermissionListener;
import com.yan.base.manager.PermissionManager;
import com.yan.base.manager.ProgressDialogManager;
import com.yan.base.manager.SnackBarAndToastManager;
import com.yan.base.widget.BaseToolbar;

/**
 * 项目名称：Base
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/7/7 10:02
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseAty extends AppCompatActivity implements PermissionListener {


    /**
     * 是否需要拦截返回键连续点击两次 一般是主页
     */
    protected boolean needCatchKeycodeBack;

    /**
     * 返回键点击次数
     */
    private int clickKeycodeBackNum = 0;

    //加载的loading
    protected ProgressDialog mProgressDialog;

    /**
     * 全局变量的存取
     */
    protected GlobalPreference mPreference;

    /**
     * 当前类，上下文
     */
    protected BaseAty mAty;

    /**
     * 资源文件
     */
    protected Resources mResources;

    /**
     * 布局加载器
     */
    protected LayoutInflater mLayoutInflater;

    /**
     * 是不是第一个启动页 应用于第三方安装
     */
    protected boolean isFirstPage;


    /**
     * 权限请求manager
     */
    protected PermissionManager mPermissionManager;

    /**
     * 弹层manager
     */
    protected SnackBarAndToastManager mSnackBarAndToastManager;

    /**
     * 加载框
     */
    protected ProgressDialogManager mProgressDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        if (isFirstPage) {//如果是通过第三方应用安装，则启动的方式有所不同
            // 部分系统会每次点击图标都重启launcher 页面 所以需要关闭launcher
            if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }
        }
        mAty = this;
        mResources = getResources();
        mPreference = GlobalPreference.getInstance();
        mLayoutInflater = LayoutInflater.from(mAty);
        mPermissionManager = new PermissionManager(mAty, this);
        AppManager.getAppManager().addActivity(this);
        mSnackBarAndToastManager = new SnackBarAndToastManager(mAty);
        mProgressDialogManager = new ProgressDialogManager(mAty);

        initView();
        initData();


    }

    /**
     * butterKnife
     */
    protected abstract void initContentView();

    /**
     * 实例化视图，设置标题的title等
     */
    protected abstract void initView();

    /**
     * 实例化数据：读取数据库数据，网络请求数据
     */
    public abstract void initData();

    /**
     * @param toolbar
     * @param showLeftIcon
     */
    public void setBaseToolbar(BaseToolbar toolbar, boolean showLeftIcon) {
        setSupportActionBar(toolbar.tb_base_tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeftIcon);
        toolbar.tb_base_tb.setBackgroundColor(toolbar.getBackgroundColor());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (toolbar.getBaseToolBarType()) {
                case BaseToolbar.STATUS_BAR_TYPE_NORMAL:
                    setTheme(R.style.AppTheme);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_IMG:
                    setTheme(R.style.AppTheme);
                    translucentStatusBar(false);

//                    toolbar.tb_base_tb.setPopupTheme(R.style.AppThemeFull);
                    break;
                case BaseToolbar.STATUS_BAR_TYPE_NO:

                    break;
                case BaseToolbar.STATUS_BAR_TYPE_FULL:
                    //保持底部
                    setTheme(R.style.AppThemeFull);
                    Window window = getWindow();
                    //去除透明的status bar
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    //绘制新的status 颜色
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    //设置新的status bar 颜色
                    window.setStatusBarColor(toolbar.getBackgroundColor());
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
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    //设置状态栏颜色
                    getWindow().setStatusBarColor(toolbar.getBackgroundColor());
//                    View decor = getWindow().getDecorView();
//                    int ui = decor.getSystemUiVisibility();
//                    getWindow().getStatusBarColor()
//                    if (lightStatusBar) {
//                        ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//                    } else {
//                        ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//                    }
//                    decor.setSystemUiVisibility(ui);
                    break;
                default:

                    break;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTheme(R.style.AppTheme);

            switch (toolbar.getBaseToolBarType()) {
                case BaseToolbar.STATUS_BAR_TYPE_NORMAL:

                    break;
                case BaseToolbar.STATUS_BAR_TYPE_IMG:

                    break;
                case BaseToolbar.STATUS_BAR_TYPE_NO:

                    break;
                case BaseToolbar.STATUS_BAR_TYPE_FULL:
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
                    View mContentChild = mContentView.getChildAt(0);
//                    int statusBarHeight = getStatusBarHeight(activity);
//
//                    removeFakeStatusBarViewIfExist(activity);
//                    addFakeStatusBarView(activity, statusColor, statusBarHeight);
//                    addMarginTopToContentChild(mContentChild, statusBarHeight);

                    if (mContentChild != null) {
                        ViewCompat.setFitsSystemWindows(mContentChild, false);
                    }
                    break;
                default:
                    break;
            }
        }
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
    void translucentStatusBar(boolean hideStatusBarBackground) {
        Window window = getWindow();

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
     * 捕获返回键
     * 子页面不需要关注 只有主页需要关注
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // 监听home键
            case KeyEvent.KEYCODE_BACK:
                if (onKeyDownBack()) return true;// 不再传递该事件
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        if (mProgressDialogManager.mSystemProgressDialog != null && mProgressDialogManager.mSystemProgressDialog.isShowing()) {
            mProgressDialogManager.mSystemProgressDialog.dismiss();
            mProgressDialogManager.mSystemProgressDialog = null;
        }

        AppManager.getAppManager().removeActivity(this);
        super.onDestroy();
    }

    //======================================permission start==============================================================
    //======================================permission start==============================================================

    /**
     * 请求权限
     *
     * @param permissions
     * @param requestCode
     */

    public void requestPermission(String[] permissions, int requestCode) {
        mPermissionManager.requestPermission(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    @Override
    public void permissionSuccess(int requestCode) {//获得了权限

    }

    /**
     * 获取权限失败
     *
     * @param requestCode
     */
    @Override
    public void permissionFail(int requestCode) {

    }
    //======================================permission end==============================================================
    //======================================permission end==============================================================

    /**
     * 返回键捕获
     *
     * @return
     */
    private boolean onKeyDownBack() {
        if (needCatchKeycodeBack) {
            if (clickKeycodeBackNum >= 1) {
                //将当前进程移到背后
                moveTaskToBack(true);
//                AppManager.getAppManager().finishAllActivity();
//                System.exit(0);//退出程序
            } else {
                clickKeycodeBackNum++;
                mSnackBarAndToastManager.showSnackBar("再次点击将退出应用");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 将回退键点击次数置为0
                        clickKeycodeBackNum = 0;
                    }
                }, 3000);
            }
            return true;
        }
        return false;
    }


    public PermissionManager getmPermissionManager() {
        return mPermissionManager;
    }

    public SnackBarAndToastManager getmSnackBarAndToastManager() {
        return mSnackBarAndToastManager;
    }

    public ProgressDialogManager getmProgressDialogManager() {
        return mProgressDialogManager;
    }
}
