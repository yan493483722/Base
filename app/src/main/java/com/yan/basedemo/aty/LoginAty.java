package com.yan.basedemo.aty;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YanZi on 2017/7/1 .
 * Description（描述）：
 * Modify(修改) :
 * Modify Description (修改描述):
 */

public class LoginAty extends BaseAty {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_login})
    void click(View view) {
        mSnackBarAndToastManager.showSnackBar("clicked btn ");
    }


}
