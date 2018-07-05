package com.yan.base;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.yan.base.toolbar.BaseToolbar;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2018/6/29.
 * describe：
 * modify:
 * modify date:
 */
public class BaseWebAty extends BaseAty {

    protected BaseToolbar btb_base_web;
    protected WebView wv_base_web;
    protected RelativeLayout rl_base_web_fail;

    protected boolean wrong;
    //WebView要加载的url
    protected String webUrl;


    @Override
    protected int setContentLayout() {
        return R.layout.aty_base_web;
    }

    @Override
    protected void initView() {
        btb_base_web = findViewById(R.id.btb_base_web);
        wv_base_web = findViewById(R.id.wv_base_web);
        rl_base_web_fail = findViewById(R.id.rl_base_web_fail);

    }

    @Override
    public void initData() {
        wv_base_web.getSettings().setJavaScriptEnabled(true);
        wv_base_web.getSettings()
                .setJavaScriptCanOpenWindowsAutomatically(true);
        wv_base_web.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放
        wv_base_web.getSettings().setLoadWithOverviewMode(true);
        wv_base_web.getSettings().setDomStorageEnabled(true);   // 开启 DOM storage API 功能
//        //设置 缓存模式
        wv_base_web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv_base_web.getSettings().setAllowFileAccess(true);  //设置可以访问文件
        wv_base_web.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wv_base_web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        wv_base_web.setWebViewClient(new BaseWebViewClient());

        wv_base_web.setWebChromeClient(new WebChromeClient() {
            //扩展支持alert事件
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    public void click() {
        wrong = false;
        wv_base_web.reload();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            // 监听home键
            case KeyEvent.KEYCODE_BACK:
                if (wv_base_web.canGoBack()) {
                    wv_base_web.goBack();
                    return true;
                }

        }
        return super.onKeyDown(keyCode, event);

    }


    public class BaseWebViewClient extends WebViewClient {

        // 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl() != null && request.getUrl().toString().equalsIgnoreCase(webUrl)) {
                return false;
            }
            view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            view.loadUrl((request.getUrl()).toString());
            return super.shouldOverrideUrlLoading(view, request.getUrl().toString());
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && url.equalsIgnoreCase(webUrl)) {
                return false;
            }
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressDialogManager.showSystemLoading(null);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressDialogManager.cancelSystemLoading();

            if (wrong) {
                if (rl_base_web_fail.getVisibility() == View.GONE) {
                    rl_base_web_fail.setVisibility(View.VISIBLE);
                }
            } else {
                if (rl_base_web_fail.getVisibility() == View.VISIBLE) {
                    rl_base_web_fail.setVisibility(View.GONE);
                }
            }

//            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            mProgressDialogManager.cancelSystemLoading();
            wrong = true;
            mSnackBarAndToastManager.showToast(mResources.getString(R.string.net_wrong_retry));
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            mProgressDialogManager.cancelSystemLoading();
            Tools.Log("wrong onReceivedSslError");
            wrong = true;
            mSnackBarAndToastManager.showToast(mResources.getString(R.string.net_wrong_retry));
            super.onReceivedSslError(view, handler, error);
        }
    }
}
