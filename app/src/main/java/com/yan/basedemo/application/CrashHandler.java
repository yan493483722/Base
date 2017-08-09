package com.yan.basedemo.application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yan.base.application.AppManager;
import com.yan.basedemo.aty.bar.MultiStatusBarAty;

/**
 * Created by YanZi on 2017/8/9.
 * describe：
 * modify:
 * modify date:
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;
    /**
     * 程序的Context对象
     */
    private Context mContext;

    private  boolean sysDeal=true;
    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }


    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);

        } else {
            Log.e("yan", "自己做相关的操作========");
            AppManager.getAppManager().finishAllActivity();
            // android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
//            Intent intent = new Intent(mContext, MultiStatusBarAty.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(intent);
        }

    }

    private boolean handleException(Throwable ex) {
        if(sysDeal){
            Log.e("yan", "系统开始处理，我不能够处理========");
        }else{
            Log.e("yan", "我开始处理，我能够处理========");
        }
        return !sysDeal;
    }
}
