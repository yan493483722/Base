package com.yan.mvp;

import android.graphics.BitmapFactory;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by YanZi on 2017/6/19.
 * describe：
 * modify:
 * modify date:
 */
public abstract class BaseCallBack<T> implements Callback {

    T t;

    public void setT(T t) {
        this.t = t;
    }

    abstract void success(T t);

    abstract void fail(T t);

    abstract void serviceError(Call call);


    public void onResponse(Call call, Response response) throws IOException {
        if (response.body() != null&&response.body().string()!=null) {//服务器root json 返回空
            BaseResponse<T>     baseResponse=new Gson().fromJson(response.body().string(),BaseResponse.class);
//            BaseResponse<T> baseResponse = JSON.parseObject(response.body().string(),
//                    new TypeReference<BaseResponse<T>>() {
//                    });
            if (baseResponse.getSuccess()) {//服务器成功 返回空
                success(baseResponse.getResult());
            } else {
                fail(baseResponse.getResult());
            }
        } else {
            serviceError(call);
        }
    }


}
