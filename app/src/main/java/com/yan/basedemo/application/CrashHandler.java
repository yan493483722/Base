package com.yan.basedemo.application;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.wangjie.androidbucket.log.Logger;
import com.yan.base.application.AppManager;
import com.yan.basedemo.MainAty;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created by YanZi on 2017/8/9.
 * describe：
 * modify:
 * modify date:
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

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

    private boolean sysDeal = false;

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
        if (!handleException(t, e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);

        } else {

            AppManager.getAppManager().finishAllActivity();

            Intent intent = new Intent();
            intent.setClass(mContext, MainAty.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

            mContext.startActivity(intent);


            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);

        }

    }

    private boolean handleException(Thread thread, Throwable throwable) {
        if (sysDeal) {
            Log.e("yan", "系统开始处理，我不能够处理========");
        } else {
            Log.e("yan", "我开始处理，我能够处理========" + getErrorInfo(thread, throwable));
        }
        return !sysDeal;
    }


    /**
     * 获取具体错误信息
     *
     * @param thread
     * @param throwable
     * @return info
     */
    private String getErrorInfo(Thread thread, Throwable throwable) {
        Writer writer = null;
        StringBuilder error = new StringBuilder("");
        PrintWriter pw = null;
        try {
            //获取设备信息
            error.append(collectDeviceInfoStr(mContext));
            //获取报错信息
            error.append("\n");
            error.append(String.format("Caught an exception in %s.  Shutting down.", new Object[]{thread}));
            error.append("\n");
            writer = new StringWriter();
            pw = new PrintWriter(writer);
            throwable.printStackTrace(pw);
            error.append(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return error.toString();
    }


    public String collectDeviceInfoStr(Context context) {
        Properties prop = collectDeviceInfo(context);
        Set deviceInfos = prop.keySet();
        StringBuilder deviceInfoStr = new StringBuilder("{\n");
        Iterator iter = deviceInfos.iterator();

        while (iter.hasNext()) {
            Object item = iter.next();
            deviceInfoStr.append("\t\t\t" + item + ":" + prop.get(item) + ", \n");
        }

        deviceInfoStr.append("}");
        return deviceInfoStr.toString();
    }

    public Properties collectDeviceInfo(Context context) {
        Properties mDeviceCrashInfo = new Properties();

        try {
            PackageManager fields = context.getPackageManager();
            PackageInfo pi = fields.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put("versionName", pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put("versionCode", Integer.valueOf(pi.versionCode));
            }
        } catch (PackageManager.NameNotFoundException var9) {
            Logger.e(TAG, "Error while collect package info", var9);
        }

        Field[] var10 = Build.class.getDeclaredFields();
        Field[] var11 = var10;
        int var4 = var10.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Field field = var11[var5];

            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), field.get(null));
            } catch (Exception var8) {
                Logger.e(TAG, "Error while collect crash info", var8);
            }
        }

        return mDeviceCrashInfo;
    }
}
