package com.yan.base.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.yan.base.BuildConfig;


/**
 * Created by YanZi on 2016/11/28.
 * describe：Application 类 初始化存在于整个生命周期的 变量
 * modify person:
 * modify date:
 * modify desc:
 */

public  class BaseApplication extends MultiDexApplication {

    public static Context mContext;

    /**
     * 用来保存当手机屏幕高度
     */
    public static int screenHeight;
    /**
     * 手机屏幕宽度
     */
    public static int screenWidth;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        AppManager.getAppManager().setApplication(this);
        getScreenHW();
        initStoreData();
        initNetWork();
        initLogger();
        initOther();
    }


    /**
     *
     */
    protected void initStoreData() {
        GlobalPreference.init(this.getApplicationContext(), this.getPackageName() + "_preference", MODE_PRIVATE);
        //TODO　database
    }

    protected  void initNetWork(){};

    protected  void initOther(){};

    protected void initLogger() {

    }

    /**
     * @return void 返回类型
     * @Title: getScreenHW
     * @Description: 初始化屏幕宽高
     * @author yan
     */
    private void getScreenHW() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
