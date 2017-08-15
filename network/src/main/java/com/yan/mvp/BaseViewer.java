package com.yan.mvp;

import okhttp3.Call;

/**
 * Created by YanZi on 2017/6/16.
 * describe：
 * modify:
 * modify date:
 */
public interface BaseViewer {

    /**
     * 网络异常
     * 设置了tag就有tag
     */
    void netError(Object tag);

    /**
     * 后台异常，404 505 或者是其他的各种异常
     */
    void serviceError(Call call);

}
