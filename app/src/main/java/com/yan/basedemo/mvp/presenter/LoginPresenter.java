package com.yan.basedemo.mvp.presenter;

import android.util.Log;

import com.yan.base.BaseAty;
import com.yan.basedemo.mvp.model.response.LoginRes;
import com.yan.basedemo.mvp.view.LoginViewer;
import com.yan.mvp.BasePresenter;
import com.yan.mvp.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public void login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.118:8010/")
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取接口实例
        LoginService loginService = retrofit.create(LoginService.class);
//调用方法得到一个Call

//<LoginRes>

        Call<BaseResponse<LoginRes>> call = loginService.login("liuqiang", "sample string 2", "aaAA1111", true);
        //进行网络请求
        call.enqueue(new Callback<BaseResponse<LoginRes>>() {

            @Override
            public void onResponse(Call<BaseResponse<LoginRes>> call, Response<BaseResponse<LoginRes>> response) {
                Log.e("yan", response.body() + "");
                viewer.success(call, response.body().getResult());
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginRes>> call, Throwable t) {
                Log.e("yan", "some thing error ");
            }
        });
        //Response<MovieSubject> response = call.execute();
    }
}
