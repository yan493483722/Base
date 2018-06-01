package com.yan.basedemo.application;

import android.content.Context;

import com.yan.base.BuildConfig;
import com.yan.base.application.BaseApplication;
import com.yan.basedemo.greendao.DaoMaster;
import com.yan.basedemo.greendao.DaoSession;
import com.yan.basedemo.network.ConstanceNet;
import com.yan.network.Retrofit2Client;

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
        //
        Retrofit2Client.setBaseUrl(ConstanceNet.ACCOUNT_URl);
//        Retrofit2Client.setContext(mContext);
        Retrofit2Client.setDebug(BuildConfig.DEBUG);
    }

    @Override
    protected void initStoreData() {
        super.initStoreData();

    }

    @Override
    protected void  initOther(){
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
        crashHandler.init(getApplicationContext());


    }


}
