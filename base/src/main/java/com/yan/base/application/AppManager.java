package com.yan.base.application;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


/**
 * activity 管理类
 *
 * @author Administrator
 */
public class AppManager {

    private static Stack<Activity> activityStack;

    //内部静态类有引用之后才会被加载到内存中，所以为懒加载
    private static class ActivityManager{
        private static AppManager appManager=new AppManager();
    }


    private AppManager() {
        activityStack = new Stack<>();
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        return ActivityManager.appManager;
    }

    /**
     * 栈中是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return activityStack.isEmpty();
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (activityStack.isEmpty()) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取前一个activity，便于返回
     *
     * @return
     */
    public Activity lastActivity() {
        if (activityStack.size() < 2) {
            return null;
        }
        return activityStack.get(activityStack.size() - 1);
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (activityStack.empty()) {
            return;
        }
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 从栈中移除指定的Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (activityStack.remove(activity)) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                if (activity != null) {
                    iterator.remove();
                    activity.finish();
                }
            }
        }
//        for (Activity activity : activityStack) {
//            if (activity.getClass().equals(cls)) {
//                finishActivity(activity);
//            }
//        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        ArrayList<Activity> activityList = new ArrayList<>(
                activityStack);
        for (int i = 0, size = activityList.size(); i < size; i++) {
            if (null != activityList.get(i)) {
                activityList.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束所有Activity保留主界面
     */
    public void finishAllActivityExcludeFirst() {
        int stackSize = activityStack.size();
        if (stackSize >= 1) {
            ArrayList<Activity> activityList = new ArrayList<>(
                    activityStack.subList(1, stackSize));
            for (int i = 0, size = activityList.size(); i < size; i++) {
                Activity activity = activityList.get(i);
                if (activity != null) {
                    activity.finish();
                    activityStack.remove(activity);
                }
            }
        }
    }

    /**
     * 结束所有Activity保留最底部几个界面
     */
    public void finishActivityKeepTop(int keepTopNum) {
        int stackSize = activityStack.size();
        if (stackSize >= keepTopNum) {
            ArrayList<Activity> activityList = new ArrayList<>(
                    activityStack.subList(keepTopNum, stackSize));
            for (int i = 0, size = activityList.size(); i < size; i++) {
                Activity activity = activityList.get(i);
                if (activity != null) {
                    activity.finish();
                    activityStack.remove(activity);
                }
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            mNotificationManager.cancel(1);
            finishAllActivity();
        } catch (Exception e) {
        } finally {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }

    }

    /**
     * 退出应用程序并停止服务
     */
    public void AppExit(Context context, Intent intent) {
        try {
            context.stopService(intent);
            finishAllActivity();
        } catch (Exception e) {
        } finally {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }

    }

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks=new Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityStopped(Activity activity) {
            Log.e("yan", activity.getLocalClassName()+" onActivityStopped");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.e("yan", activity.getLocalClassName()+" onActivityStarted");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.e("yan",activity.getLocalClassName()+ " onActivitySaveInstanceState");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.e("yan", activity.getLocalClassName()+" onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.e("yan",activity.getLocalClassName()+ " onActivityPaused");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.e("yan",activity.getLocalClassName()+ " onActivityDestroyed");
            removeActivity(activity);
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            addActivity(activity);
            Log.e("yan", activity.getLocalClassName()+" onActivityCreated"+ "stack size=" +activityStack.size());

        }

    };

    public Application.ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return activityLifecycleCallbacks;
    }
}