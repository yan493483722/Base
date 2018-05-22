package com.yan.basedemo.aty;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yan.base.BaseAty;
import com.yan.base.listener.BaseDialogThreeMoreBtnClickListener;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.uitls.FileUtil;
import com.yan.basedemo.R;
import com.yan.basedemo.mvp.model.response.LoginRes;
import com.yan.basedemo.mvp.presenter.LoginPresenter;
import com.yan.basedemo.mvp.view.LoginViewer;
import com.yan.network.download.apk.APKUpdateManager;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YanZi on 2017/7/1 .
 * Description（描述）：
 * Modify(修改) :
 * Modify Description (修改描述):
 */

public class LoginAty extends BaseAty implements LoginViewer {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btb_login)
    BaseToolbar btbLogin;

    LoginPresenter loginPresenter;
    //
    private static final String TEST_URL = "http://172.17.0.120:8107/Api/Account/Login2";


    APKUpdateManager apkUpdateManager;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_login;
    }

    @Override
    protected void initView() {
        btbLogin.setTitleText("登录");
        setBaseToolbar(btbLogin, true);
        btbLogin.setBaseToolbarListener(new BaseToolbar.BaseToolbarListener() {
            @Override
            public void clickLeft(int type) {
                onBackPressed();
            }

            @Override
            public void clickRight(int type) {

            }
        });

        loginPresenter = new LoginPresenter(this, this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_login, R.id.btn_download, R.id.btn_upload})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.login3();
                break;
            case R.id.btn_download:
                String download = "http://58.215.175.244/qkonline/qkonline.apk";

                String saveFilePath = FileUtil.getBasePath(mAty) + File.separator + "apkfile";
                String fileName = "qkonline.apk";
                if (apkUpdateManager == null) {
                    apkUpdateManager = new APKUpdateManager(mAty, download, saveFilePath, fileName, mLayoutInflater);
                    apkUpdateManager.setTitle("发现新版本");
                    apkUpdateManager.setContent("版本更新啦！！！" +
                            "\n 版本号:V1.2.0" +
                            "\n 更新内容：" +
                            "\n   1.修复部分bug" +
                            "\n   2.优化部分用户体验" +
                            "\n   3.增加炫酷模式"
                    );
                    ArrayList<String> btnTexts = new ArrayList<>();
                    btnTexts.add("立即更新");
                    btnTexts.add("当前版本不再提醒");
                    btnTexts.add("下次再说");
                    apkUpdateManager.setBtnTexts(btnTexts);
                }
                apkUpdateManager.showUpdateDialog(new BaseDialogThreeMoreBtnClickListener() {
                    @Override
                    public void clickBtn(int position, int tag) {
                        if (position == 0) {
                            apkUpdateManager.download();
                        }
                    }
                });
                break;
            case R.id.btn_upload:
                startActivity(new Intent(mAty, UploadAty.class));
                break;
        }

        mSnackBarAndToastManager.showSnackBar("clicked btn ");
    }


    @Override
    public void netError(Object tag) {

    }

    @Override
    public void serviceError(retrofit2.Call call) {

    }

    @Override
    public void success(retrofit2.Call call, LoginRes loginRes) {
        mSnackBarAndToastManager.showSnackBar("ssssss");
        Log.e("yan", "this is success in activity  " + loginRes.toString());
    }

    @Override
    public void fail(retrofit2.Call call) {

    }
}
