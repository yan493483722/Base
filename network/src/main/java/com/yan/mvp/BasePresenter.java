package com.yan.mvp;

import android.util.Log;

import com.google.gson.Gson;
import com.yan.base.BaseAty;
import com.yan.base.BuildConfig;
import com.yan.base.uitls.Tools;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YanZi on 2017/6/16.
 * describe：
 * modify:
 * modify date:
 */
public class BasePresenter<T extends BaseViewer> {

    protected T viewer;

    protected BaseAty baseAty;

    protected OkHttpClient mOkHttpClient;

    Retrofit mRetrofit;

    public BasePresenter(BaseAty baseAty, T t) {
        this.baseAty = baseAty;
        this.viewer = t;
        HttpLoggingInterceptor httpLoggingInterceptor;
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        } else {
            httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        mOkHttpClient = new OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        mRetrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(mOkHttpClient)
                //设置baseUrl,注意，baseUrl必须后缀"/"
                .baseUrl("https://api.github.com/")

                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//    }

//    protected boolean noNetAndCallBack(Object tag) {
//        if (!Tools.isOnline(baseAty)) {
//            viewer.netError(tag);
//            return true;
//        } else {
//            return false;
//        }
//    }

    }

    protected void sendRequestModel(Object requestModel, String url, boolean autoShowLoading, Object tag, Callback callBack) {
        sendRequestModel(null, requestModel, url, autoShowLoading, tag, callBack);
    }

    protected void sendRequestModel(String token, Object requestModel, String url, boolean autoShowLoading, Object tag, Callback callBack) {

        OkHttpClient mOkHttpClient;

        mOkHttpClient = new OkHttpClient().newBuilder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        if (autoShowLoading) {
            baseAty.getmProgressDialogManager().showSystemLoading("加载中");
        }
        //        RequestBody requestBody = new FormBody.Builder()
//                .add("usernameOrEmailAddress", usernameOrEmailAddress)
//                .add("validateCode", "sample string 2")
//                .add("password", password)
//                .add("rememberMe", "true")
//                .build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                new Gson().toJson(requestModel)
        );
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
//
//                JSON.toJSONString(requestModel, SerializerFeature.WriteMapNullValue));
//创建一个Request
        Request request;
        if (Tools.isNull(token)) {
            request = new Request.Builder()
                    .url(url)
                    .post(body).tag(tag).build();
            Log.e("yan", "接口没有传入token或token为空");
        } else {
            request = new Request.Builder()
                    .url(url).header("Authorization", "Bearer " + token)
                    .post(body).tag(tag).build();
        }
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callBack);
//请求加入调度 enqueue是异步execute是同步
//        call.enqueue( new Callback() {
//            public void onFailure(Call call, IOException e) {
//                if (autoShowLoading) {
//                    baseAty.getmProgressDialogManager().cancelSystemLoading();
//                }
        //可以得到request进行接下来的操作
        //  call.request();
//                Log.e("yan", "无法连接服务器" + call.request().toString());
//                viewer.serviceError(call);
//            }

//            public void onResponse(Call call, Response response) throws IOException {
//                if (autoShowLoading) {
//                    baseAty.getmProgressDialogManager().cancelSystemLoading();
//                }
//                if (response.body() != null) {//服务器root json 返回空
//
//                    BaseResponse<D> baseResponse = JSON.parseObject(response.body().string(),
//                            new TypeReference<BaseResponse<D>>() {
//                            });
//                    requestCallback.success(baseResponse.getResult());
//                }
//                    if (baseResponse.getSuccess()) {//服务器成功 返回空
//                        ((LoginViewer) viewer).loginSuccess(baseResponse.getResult());
//                    } else {
//                        ((LoginViewer) viewer).loginFail();
//                    }
//                } else {
//                    ((LoginViewer) viewer).loginFail();
//                }
//            }
//        });


    }


    protected <D> void sendRequestModel(Object requestModel, String url, final boolean autoShowLoading, Object tag) {
//        if (autoShowLoading) {
//            baseAty.getmProgressDialogManager().showSystemLoading("加载中");
//        }
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(requestModel));
////创建一个Request
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(body).tag(tag)
//                .build();
//
//        Call call = mOkHttpClient.newCall(request);
////请求加入调度 enqueue是异步execute是同步
//        call.enqueue(new BaseCallBack<D>() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("yan", "无法连接服务器" + call.request().toString());
//                viewer.serviceError(call);
//            }
//
//            @Override
//            void success(D d) {
//                if (autoShowLoading) {
//                    baseAty.getmProgressDialogManager().cancelSystemLoading();
//                }
//            }
//
//            @Override
//            void fail(D d) {
//                if (autoShowLoading) {
//                    baseAty.getmProgressDialogManager().cancelSystemLoading();
//                }
//            }
//
//            @Override
//            void serviceError(Call call) {
//                if (autoShowLoading) {
//                    baseAty.getmProgressDialogManager().cancelSystemLoading();
//                }
//                serviceError(call);
//            }
//        });
    }


}

