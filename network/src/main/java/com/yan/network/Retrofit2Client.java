package com.yan.network;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YanZi on 2017/8/20 .
 * Description（描述）：
 * retrofit 使用详解
 * http://blog.csdn.net/carson_ho/article/details/73732076
 * Modify(修改) :
 * Modify Description (修改描述):
 */

public class Retrofit2Client {

    private static final int DEFAULT_TIMEOUT = 30;
//    private BaseApiService apiService;


    private Map<String,Retrofit>  retrofitMap;

    private static Map<String, String> headers;
    private static Map<String, String> parameters;
    private static Retrofit.Builder retrofitBuilder;
    private static OkHttpClient.Builder okhttpBuilder;

    private static OkHttpClient okHttpClient;

    private static String mBaseUrl;


    private  Context mContext;
    private static boolean isDebug;


    private  Retrofit2Client mInstance;

    private static Retrofit retrofit;

    private Cache cache = null;
    private File httpCacheDirectory;


    public static void setBaseUrl(String baseUrl) {
        Retrofit2Client.mBaseUrl = baseUrl;
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    public  void setContext(Context context) {
        if(context instanceof Activity) {
            mContext  = (context).getApplicationContext();
        } else {
            mContext = context;
        }
    }

    private static class SingletonHolder {
        private static Retrofit2Client INSTANCE = new Retrofit2Client(mBaseUrl);
    }

    public static Retrofit2Client getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static Retrofit2Client getInstance(String url) {
        return new Retrofit2Client(url);
    }

    public static Retrofit2Client getInstance(String url, Map<String, String> headers) {
        return new Retrofit2Client(url, headers);
    }


    private Retrofit2Client(String url) {
        this(url, null);
    }

    private Retrofit2Client(String baseUrl, Map<String, String> headers) {

        if (baseUrl == null) {
            throw new IllegalStateException("Base URL required.");
        }

        if (okhttpBuilder == null) {
            throw new IllegalStateException("okhttpBuilder required.");
        }

        if (retrofitBuilder == null) {
            throw new IllegalStateException("retrofitBuilder required.");
        }

        if (!TextUtils.isEmpty(baseUrl)) {
            mBaseUrl = baseUrl;
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "net_cache");
        }


        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BaseInterceptor(headers))
                .addNetworkInterceptor(getHttpLoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                // 设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .build();

    }

    /**
     * ApiBaseUrl
     *
     * @param newApiBaseUrl
     */
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        mBaseUrl = newApiBaseUrl;
        retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl);
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        if (isDebug) {
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        } else {
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        }
    }

//    /**
//     * addcookieJar
//     */
//    public static void addCookie() {
//        okHttpClient.newBuilder().cookieJar(new NovateCookieManger(mContext)).build();
//        retrofit = builder.client(okHttpClient).build();
//    }
//
//    /**
//     * ApiBaseUrl
//     *
//     * @param newApiHeaders
//     */
//    public static void changeApiHeader(Map<String, String> newApiHeaders) {
//
//        okHttpClient.newBuilder().addInterceptor(new BaseInterceptor(newApiHeaders)).build();
//        builder.client(httpClient.build()).build();
//    }
//
//    /**
//     * create BaseApi  defalte ApiManager
//     *
//     * @return ApiManager
//     */
//    public RetrofitClient createBaseApi() {
//        apiService = create(BaseApiService.class);
//        return this;
//    }
//
//    /**
//     * create you ApiService
//     * Create an implementation of the API endpoints defined by the {@code service} interface.
//     */
//    public <T> T create(final Class<T> service) {
//        if (service == null) {
//            throw new RuntimeException("Api service is null!");
//        }
//        return retrofit.create(service);
//    }
//
//    public Subscription getData(Subscriber<IpResult> subscriber, String ip) {
//        return apiService.getData(ip)
//                .compose(schedulersTransformer())
//                .compose(transformer())
//                .subscribe(subscriber);
//    }
//
//    public Subscription get(String url, Map parameters, Subscriber<IpResult> subscriber) {
//
//        return apiService.executeGet(url, parameters)
//                .compose(schedulersTransformer())
//                .compose(transformer())
//                .subscribe(subscriber);
//    }
//
//    public void post(String url, Map<String, String> parameters, Subscriber<ResponseBody> subscriber) {
//        apiService.executePost(url, parameters)
//                .compose(schedulersTransformer())
//                .compose(transformer())
//                .subscribe(subscriber);
//    }
//
//    public Subscription json(String url, RequestBody jsonStr, Subscriber<IpResult> subscriber) {
//
//        return apiService.json(url, jsonStr)
//                .compose(schedulersTransformer())
//                .compose(transformer())
//                .subscribe(subscriber);
//    }
//
//    public void upload(String url, RequestBody requestBody, Subscriber<ResponseBody> subscriber) {
//        apiService.upLoadFile(url, requestBody)
//                .compose(schedulersTransformer())
//                .compose(transformer())
//                .subscribe(subscriber);
//    }
//
//    public void download(String url, final CallBack callBack) {
//        apiService.downloadFile(url)
//                .compose(schedulersTransformer())
//                .compose(transformer())
//                .subscribe(new DownSubscriber<ResponseBody>(callBack));
//    }
//
//    Observable.Transformer schedulersTransformer() {
//        return new Observable.Transformer() {
//
//
//            @Override
//            public Object call(Object observable) {
//                return ((Observable) observable).subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//
//           /* @Override
//            public Observable call(Observable observable) {
//                return observable.subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }*/
//        };
//    }
//
//    <T> Observable.Transformer<T, T> applySchedulers() {
//        return (Observable.Transformer<T, T>) schedulersTransformer();
//    }
//
//    public <T> Observable.Transformer<BaseResponse<T>, T> transformer() {
//
//        return new Observable.Transformer() {
//
//            @Override
//            public Object call(Object observable) {
//                return ((Observable) observable).map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>());
//            }
//        };
//    }
//
//    public <T> Observable<T> switchSchedulers(Observable<T> observable) {
//        return observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    private static class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
//        @Override
//        public Observable<T> call(Throwable t) {
//            return Observable.error(ExceptionHandle.handleException(t));
//        }
//    }
//
//    private class HandleFuc<T> implements Func1<BaseResponse<T>, T> {
//        @Override
//        public T call(BaseResponse<T> response) {
//            if (!response.isOk())
//                throw new RuntimeException(response.getCode() + "" + response.getMsg() != null ? response.getMsg() : "");
//            return response.getData();
//        }
//    }
//
//
//    /**
//     * /**
//     * execute your customer API
//     * For example:
//     * MyApiService service =
//     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
//     * <p>
//     * RetrofitClient.getInstance(MainActivity.this)
//     * .execute(service.lgon("name", "password"), subscriber)
//     * * @param subscriber
//     */
//
//    public static <T> T execute(Observable<T> observable, Subscriber<T> subscriber) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//        return null;
//    }
//
//
//    /**
//     * DownSubscriber
//     *
//     * @param <ResponseBody>
//     */
//    class DownSubscriber<ResponseBody> extends Subscriber<ResponseBody> {
//        CallBack callBack;
//
//        public DownSubscriber(CallBack callBack) {
//            this.callBack = callBack;
//        }
//
//        @Override
//        public void onStart() {
//            super.onStart();
//            if (callBack != null) {
//                callBack.onStart();
//            }
//        }
//
//        @Override
//        public void onCompleted() {
//            if (callBack != null) {
//                callBack.onCompleted();
//            }
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            if (callBack != null) {
//                callBack.onError(e);
//            }
//        }
//
//        @Override
//        public void onNext(ResponseBody responseBody) {
//            DownLoadManager.getInstance(callBack).writeResponseBodyToDisk(mContext, (okhttp3.ResponseBody) responseBody);
//
//        }
//    }

}
