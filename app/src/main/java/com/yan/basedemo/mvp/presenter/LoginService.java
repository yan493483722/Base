package com.yan.basedemo.mvp.presenter;

import com.yan.basedemo.mvp.model.response.LoginRes;
import com.yan.mvp.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by YanZi on 2017/8/16.
 * describe：
 * modify:
 * modify date:
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("api/Account/Authenticate")
    Call<BaseResponse<LoginRes>> login(@Field("usernameOrEmailAddress") String usernameOrEmailAddress,
                                       @Field("validateCode") String validateCode,
                                       @Field("password") String password,
                                       @Field("rememberMe") boolean rememberMe);



    @FormUrlEncoded
    @POST("api/Account/Authenticate")
    Call<BaseResponse<LoginRes>> login2();
}
