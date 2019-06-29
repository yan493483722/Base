package com.yan.base.manager;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.yan.base.BaseAty;
import com.yan.base.listener.PermissionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanZi on 2017/4/12.
 * describe：在所在的activity onRequestPermissionsResult方法，6.0以下也可以直接调用
 * 中调用 permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
 * modify:
 * modify date:
 */
public class PermissionManager {
    //default code
    private int REQUEST_CODE_PERMISSION = 0x00099;

    BaseAty baseAty;

    PermissionListener listener;


    public PermissionManager(BaseAty baseAty, PermissionListener listener) {
        this.baseAty = baseAty;
        this.listener = listener;
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限的数组 new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
     *                    CODE_FOR_WRITE_PERMISSION
     * @param requestCode 请求权限的请求码  requestCode 建议设置范围为10-99
     */
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {//是否有这个权限
            if (listener != null) {
                listener.permissionSuccess(REQUEST_CODE_PERMISSION);  //有权限进入成功的回调
            }
        } else {//没有权限去请求权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                List<String> needPermissions = getDeniedPermissions(permissions);
                // 第二个参数 不要设置为-1 如果为-1的话第一次不会执行
                baseAty.requestPermissions(needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
            } else {//6.0以下
                if (listener != null) {
                    listener.permissionSuccess(REQUEST_CODE_PERMISSION);  //有权限进入成功的回调
                }
            }
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (baseAty.checkSelfPermission(permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(baseAty, permission) !=
                    PackageManager.PERMISSION_GRANTED || baseAty.shouldShowRequestPermissionRationale(permission)) {
                needRequestPermissionList.add(permission);
            }

        }
        return needRequestPermissionList;
    }

    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {//确认获得了改权限
                if (listener != null) {
                    listener.permissionSuccess(REQUEST_CODE_PERMISSION);
                }
            } else {
                if (listener != null) {
                    listener.permissionFail(REQUEST_CODE_PERMISSION);
                }
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
//        showAlertDialogDouble(mResources.getString(R.string.get_permission_fail_title), mResources.getString(R.string.get_permission_fail_content)
//                , mResources.getString(R.string.base_ac_cancel), mResources.getString(R.string.base_ac_confirm), new BaseDoubleBtnClickListener() {
//                    @Override
//                    public void clickRightBtn() {//确定 打开设置界面
//                        startAppSettings();
//                    }
//
//                    @Override
//                    public void clickLeftBtn() {
//
//                    }
//                }
//
//        );
    }

}
