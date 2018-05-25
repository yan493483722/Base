package com.yan.basedemo.greendao;

import android.content.Context;

import com.yan.basedemo.bean.Dog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by YanZi on 2018/5/24.
 * describe：
 * modify:
 * modify date:
 */
public class DogDaoOperation {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param dog
     */
    public static void insertData(Context context, Dog dog) {
        GreenDaoManager.getDaoSession(context).getDogDao().insert(dog);
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<Dog> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        GreenDaoManager.getDaoSession(context).getDogDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param dog
     */
    public static void saveData(Context context, Dog dog) {
        GreenDaoManager.getDaoSession(context).getDogDao().save(dog);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param dog 删除具体内容
     */
    public static void deleteData(Context context,  Dog dog) {
        GreenDaoManager.getDaoSession(context).getDogDao().delete(dog);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        GreenDaoManager.getDaoSession(context).getDogDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        GreenDaoManager.getDaoSession(context).getDogDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param dog
     */
    public static void updateData(Context context, Dog dog) {
        GreenDaoManager.getDaoSession(context).getDogDao().update(dog);
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<Dog> queryAll(Context context) {
        QueryBuilder<Dog> builder = GreenDaoManager.getDaoSession(context).getDogDao().queryBuilder();

        return builder.build().list();
    }



    /**
     *  分页加载
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public static List<Dog> queryPaging( int pageSize, int pageNum,Context context){
        DogDao dogDao = GreenDaoManager.getDaoSession(context).getDogDao();
        List<Dog> listMsg = dogDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }
}
