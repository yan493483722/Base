package com.yan.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.yan.base.application.AppManager;
import com.yan.base.application.GlobalPreference;
import com.yan.base.listener.PermissionListener;
import com.yan.base.manager.PermissionManager;
import com.yan.base.manager.ProgressDialogManager;
import com.yan.base.manager.SnackBarAndToastManager;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.toolbar.BaseToolbarUtil;

import java.io.File;

import butterknife.ButterKnife;

/**
 * 项目名称：Base
 * 类描述：
 * 创建人：YanZi
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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAty = this;
        mResources = getResources();
        mPreference = GlobalPreference.getInstance();
        mLayoutInflater = LayoutInflater.from(mAty);
        mPermissionManager = new PermissionManager(mAty, this);
        mSnackBarAndToastManager = new SnackBarAndToastManager(mAty);
        mProgressDialogManager = new ProgressDialogManager(mAty);

        setContentView(setContentLayout());
        ButterKnife.bind(this);
        if (isFirstPage) {//如果是通过第三方应用安装，则启动的方式有所不同
            // 部分系统会每次点击图标都重启launcher 页面 所以需要关闭launcher
            if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }
        }
//        Logger.e("this "+BuildConfig.APPLICATION_ID);

        initView();
        initData();

    }

    /**
     * 设置content的布局
     */
    protected abstract @LayoutRes
    int setContentLayout();

    /**
     * 实例化视图，设置标题的title等
     */
    protected abstract void initView();

    /**
     * 实例化数据：读取数据库数据，网络请求数据
     */
    public abstract void initData();

    /**
     * 普通页面设置baseToolbar
     *
     * @param toolbar
     * @param showLeftIcon
     */
    public void setBaseToolbar(BaseToolbar toolbar, boolean showLeftIcon) {
        setSupportActionBar(toolbar.tb_base_tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeftIcon);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            BaseToolbarUtil.setBaseToolbar(toolbar, mAty, false);
        }
    }

    /**
     * 两边有侧滑时设置baseToolbar
     * 注意：
     * 1.根布局 android.support.v4.widget.DrawerLayout 的DrawerLayout 要设置 android:fitsSystemWindows="true"
     * 2.左右滑动菜单根本局 为 android.support.design.widget.NavigationView 勿使用其他作为根布局
     *
     * @param toolbar
     * @param showLeftIcon
     */
    public void setBaseToolbarSlide(BaseToolbar toolbar, boolean showLeftIcon) {
        setSupportActionBar(toolbar.tb_base_tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeftIcon);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            BaseToolbarUtil.setBaseToolbar(toolbar, mAty, true);
        }
    }

    public void setBaseToolbarInFragment() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                mChildView.setFitsSystemWindows(false);
                ViewCompat.requestApplyInsets(mChildView);
            }
        }
    }

    public LayoutInflater getmLayoutInflater() {
        return mLayoutInflater;
    }

    /**
     * 两边有侧滑时设置baseToolbar
     * 注意：
     * 1.根布局 android.support.v4.widget.DrawerLayout 的DrawerLayout 要设置 android:fitsSystemWindows="true"
     * 2.左右滑动菜单根本局 为 android.support.design.widget.NavigationView 勿使用其他作为根布局
     *
     * @param toolbar
     * @param showLeftIcon
     */
    public void setBaseToolbarStretchable(BaseToolbar toolbar, boolean showLeftIcon) {
        setBaseToolbarInFragment();
        setSupportActionBar(toolbar.tb_base_tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeftIcon);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            BaseToolbarUtil.setFragmentBaseToolbar(toolbar, mAty);
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(this);
        if (mProgressDialogManager.getSystemProgressDialog() != null && mProgressDialogManager.getSystemProgressDialog().isShowing()) {
            mProgressDialogManager.getSystemProgressDialog().dismiss();
            mProgressDialogManager.release();
        }
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
//                moveTaskToBack(true);
                AppManager.getAppManager().finishAllActivity();
                System.exit(0);//退出程序
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

    @Override
    public void finish() {
        hidePan();
        super.finish();
    }

    public SnackBarAndToastManager getmSnackBarAndToastManager() {
        return mSnackBarAndToastManager;
    }

    public ProgressDialogManager getmProgressDialogManager() {
        return mProgressDialogManager;
    }

    public void displayImage(String url, ImageView imageView) {
        Glide.with(getApplicationContext())//
                .load(url)//
                .into(imageView);
    }

    public void displayImageNoCache(String url, ImageView imageView) {
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageView);

    }


    public void displayImageNoCache(File file, ImageView imageView) {
        Glide.with(this)
                .load(file)
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageView);

    }


    protected void hidePan() {
        InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        View view = (mAty).getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
