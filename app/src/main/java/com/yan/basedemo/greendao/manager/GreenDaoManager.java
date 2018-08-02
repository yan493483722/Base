package com.yan.basedemo.greendao.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yan.basedemo.greendao.DaoMaster;
import com.yan.basedemo.greendao.DaoSession;

/**
 * Created by YanZi on 2018/5/23.
 * describe：
 * modify:
 * modify date:
 */
public class GreenDaoManager {
    // 是否加密
    public static final boolean ENCRYPTED = false;

    private static final String DB_NAME = "BaseDemo.db";
    private static GreenDaoManager mDbManager;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static GreenDaoManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (GreenDaoManager.class) {
                if (null == mDbManager) {
                    mDbManager = new GreenDaoManager(context);
                }
            }
        }
        return mDbManager;
    }

    private GreenDaoManager(Context context) {
        // 初始化数据库信息
//        mDevOpenHelper =  new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "user-db");
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        mDevOpenHelper = new MySQLiteOpenHelper(context, DB_NAME,
                null);

//        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, DB_NAME,
//                null);
        getDaoMaster(context);
        getDaoSession(context);
    }


    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        //        Database db = ENCRYPTED ? mDevOpenHelper.getEncryptedWritableDb("super-secret") : mDevOpenHelper.getWritableDb();
        //
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
//        Database db = ENCRYPTED ? mDevOpenHelper.getEncryptedWritableDb("super-secret") : mDevOpenHelper.getWritableDb();
//
        return mDevOpenHelper.getWritableDatabase();
    }


    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (GreenDaoManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoMaster
     *
     * 判断是否存在数据库，如果没有则创建数据库
     * @param context
     * @return
     */
//    public static DaoMaster getDaoMaster(Context context) {
//        if (null == mDaoMaster) {
//            synchronized (GreenDaoManager.class) {
//                if (null == mDaoMaster) {
//                    MyOpenHelper helper = new MyOpenHelper(context,DB_NAME,null);
//                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
//                }
//            }
//        }
//        return mDaoMaster;
//    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (GreenDaoManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }
}
