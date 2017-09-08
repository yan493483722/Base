package com.yan.base.application;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by YanZi QQ：493483722 on 2017/7/24.
 * describe：AppManager  activity 管理类
 * usage： Application onCreate()
 * 中registerActivityLifecycleCallbacks(AppManager.getAppManager().getActivityLifecycleCallbacks());
 * modify:
 * modify date:
 * modify describe：
 */
public class AppManager {

    private static final String TAG = ActivityManager.class.getSimpleName();

    private static Stack<WeakReference<Activity>> activityStack;

    //内部静态类有引用之后才会被加载到内存中，所以为懒加载
    private static class ActivityManager {
        private static AppManager appManager = new AppManager();
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
        activityStack.add(new WeakReference<>(activity));
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return getLastElement();
    }

    private Activity getLastElement() {
        if (activityStack.isEmpty()) {
            return null;
        }
        Activity activity;
        do {
            activity = activityStack.lastElement().get();
            if (activity == null) {//当前Activity 因为内存不足被销毁了，顺序获取下一个
                activityStack.pop();
            }
            if (activityStack.isEmpty()) {
                return null;
            }
        } while (activity != null);
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
        return getLastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (activityStack.empty()) {
            return;
        }
        Activity activity = getLastElement();
        finishActivity(activity);
    }

    /**
     * 从栈中移除指定的Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            Iterator<WeakReference<Activity>> iterator = activityStack.iterator();//迭代器模式效率高于增强for循环
            while (iterator.hasNext()) {
                Activity activity1 = iterator.next().get();
                if (activity1 != null) {
                    if (activity == activity1) {//指针想同 同一个
                        iterator.remove();
                    }
                }
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            removeActivity(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Iterator<WeakReference<Activity>> iterator = activityStack.iterator();//迭代器模式效率高于增强for循环
        while (iterator.hasNext()) {
            Activity activity = iterator.next().get();
            if (activity != null) {
                if (activity.getClass().equals(cls)) {
                    iterator.remove();
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        ArrayList<WeakReference<Activity>> activityList = new ArrayList<>(
                activityStack);
        for (int i = 0, size = activityList.size(); i < size; i++) {
            if (null != activityList.get(i).get()) {
                activityList.get(i).get().finish();
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
            ArrayList<WeakReference<Activity>> activityList = new ArrayList<>(
                    activityStack.subList(1, stackSize));
            for (int i = 0, size = activityList.size(); i < size; i++) {
                WeakReference<Activity> weakReference = activityList.get(i);
                activityStack.remove(weakReference);
                if (weakReference.get() != null) {
                    weakReference.get().finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity保留最底部几个界面
     */
    public void finishActivityKeepBottom(int keepBottomNum) {
        int stackSize = activityStack.size();
        if (stackSize >= keepBottomNum) {
            ArrayList<WeakReference<Activity>> activityList = new ArrayList<>(
                    activityStack.subList(keepBottomNum, stackSize));
            for (int i = 0, size = activityList.size(); i < size; i++) {
                WeakReference<Activity> weakReference = activityList.get(i);
                activityStack.remove(weakReference);
                if (weakReference.get() != null) {
                    weakReference.get().finish();
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

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityStopped(Activity activity) {
            Log.e(TAG, activity.getLocalClassName() + " onActivityStopped");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.e(TAG, activity.getLocalClassName() + " onActivityStarted");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.e(TAG, activity.getLocalClassName() + " onActivitySaveInstanceState");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.e(TAG, activity.getLocalClassName() + " onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.e(TAG, activity.getLocalClassName() + " onActivityPaused");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            removeActivity(activity);
            Log.e(TAG, activity.getLocalClassName() + " onActivityDestroyed ==>" + TAG + " stack size=" + activityStack.size());
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            addActivity(activity);
            Log.e(TAG, activity.getLocalClassName() + " onActivityCreated ==>" + TAG + " stack size=" + activityStack.size());

        }

    };

    public Application.ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return activityLifecycleCallbacks;
    }
}