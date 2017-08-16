package com.yan.basedemo.aty;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yan.base.BaseAty;
import com.yan.base.toolbar.BaseToolbar;
import com.yan.basedemo.R;
import com.yan.basedemo.mvp.model.response.LoginRes;
import com.yan.basedemo.mvp.presenter.LoginPresenter;
import com.yan.basedemo.mvp.view.LoginViewer;

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


    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_login);
        ButterKnife.bind(this);
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

    @OnClick({R.id.btn_login})
    void click(View view) {
        loginPresenter.login();
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
        Log.e("yan", "this is success in activity  " + loginRes.toString());
    }

    @Override
    public void fail(retrofit2.Call call) {

    }
}
