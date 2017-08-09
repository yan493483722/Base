package com.yan.basedemo.application;

import android.content.Context;

import com.yan.base.application.BaseApplication;

/**
 * Created by YanZi on 2017/4/13.
 * describe：
 * modify:
 * modify date:
 */
public class BaseDemoApplication extends BaseApplication {

    public static Context mContext;

    @Override
    protected void initNetWork() {
        mContext=   getApplicationContext();
    }

    @Override
    protected void  initOther(){
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
        crashHandler.init(getApplicationContext());
    }
}
