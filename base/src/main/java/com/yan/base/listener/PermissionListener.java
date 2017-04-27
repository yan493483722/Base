package com.yan.base.listener;

/**
 * Created by YanZi on 2017/4/12.
 * describe：
 * modify:
 * modify date:
 */
public interface PermissionListener {

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    void permissionSuccess(int requestCode);

    /**
     * 权限获取失败
     * 如果没有权限直接退出应用，则不需要super.permissionFail();
     * 直接 在permissionFail 中关闭所有页面并退出
     * 如果没有权限继续玩该应用，则可选super.permissionFail();
     * 提醒用户该功能无法使用，去父页面打开使用
     *
     * @param requestCode
     */
    void permissionFail(int requestCode);
}
