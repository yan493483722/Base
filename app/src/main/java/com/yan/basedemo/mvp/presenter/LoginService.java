package com.yan.basedemo.mvp.presenter;

import com.yan.basedemo.mvp.model.request.LoginReq;
import com.yan.basedemo.mvp.model.response.LoginRes;
import com.yan.mvp.BaseResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by YanZi on 2017/8/16.
 * describe：对于 Retrofit 的 使用http://www.jianshu.com/p/308f3c54abdd
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
    Call<BaseResponse<LoginRes>> login2(@FieldMap Map<String, String> params);


    @POST("api/Account/Authenticate")
    Call<BaseResponse<LoginRes>> login3(@Body LoginReq loginReq);
}
