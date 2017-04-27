package com.yan.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import com.yan.base.application.AppManager;
import com.yan.base.application.GlobalPreference;
import com.yan.base.listener.PermissionListener;
import com.yan.base.manager.BaseDialogManager;
import com.yan.base.manager.PermissionManager;
import com.yan.base.manager.ProgressDialogManager;
import com.yan.base.manager.SnackBarAndToastManager;

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
     * 弹窗 包含单个和多个按钮
     */
    protected BaseDialogManager mDialogManager;

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
        mDialogManager = new BaseDialogManager(mAty, mLayoutInflater);
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
     * @param permissionListener
     */
    public void requestPermission(String[] permissions, int requestCode, PermissionListener permissionListener) {
        mPermissionManager.requestPermission(permissions, requestCode, permissionListener);
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
//                        moveTaskToBack(true);
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


}
