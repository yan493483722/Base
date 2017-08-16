package com.yan.basedemo.mvp.view;

import com.yan.basedemo.mvp.model.response.LoginRes;
import com.yan.mvp.BaseViewer;

import retrofit2.Call;


/**
 * Created by YanZi on 2017/8/15.
 * describe：
 * modify:
 * modify date:
 */
public interface LoginViewer extends BaseViewer {

    void success(Call call, LoginRes loginRes);

    void  fail(Call call);
}
