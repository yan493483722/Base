package com.yan.basedemo.mvp.presenter;

import com.yan.base.BaseAty;
import com.yan.basedemo.mvp.view.LoginViewer;
import com.yan.mvp.BasePresenter;

/**
 * Created by YanZi on 2017/8/15.
 * describe：
 * modify:
 * modify date:
 */
public class LoginPresenter extends BasePresenter<LoginViewer> {


    public LoginPresenter(BaseAty baseAty, LoginViewer loginViewer) {
        super(baseAty, loginViewer);
    }

}
