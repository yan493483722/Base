package com.yan.base.application;

import android.app.Application;
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

public  class BaseApplication extends Application {

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
    private void initStoreData() {
        GlobalPreference.init(this.getApplicationContext(), this.getPackageName() + "_preference", MODE_PRIVATE);
        //TODO　database
    }

    protected  void initNetWork(){};

    protected  void initOther(){};

    private void initLogger() {
        Log.e("BaseApplication", "BaseApplication BuildConfig.DEBUG" + BuildConfig.DEBUG);
//        if (BuildConfig.DEBUG) {//debug打开，preview 也是打开的 debuggable 为true release 关闭
        Logger.init("yan")                                  // default PRETTYLOGGER or use just init()
                .methodCount(1)                             // default 2
                .logLevel(LogLevel.FULL)                    // default LogLevel.FULL
                .methodOffset(0);                           // default 0
//        } else {
//            Logger.init("yan").logLevel(LogLevel.NONE);
//        }
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

}
