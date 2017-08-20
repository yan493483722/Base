package com.yan.network;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * Created by YanZi on 2017/8/20 .
 * Description（描述）：
 * Modify(修改) :
 * Modify Description (修改描述):
 */

public class NetworkException {

    private static final int HTTP = 400;// 请求无效
    private static final int UNAUTHORIZED = 401;//未被授权的, 未经认可的
    private static final int FORBIDDEN = 403;// 禁止访问
    private static final int NOT_FOUND = 404;//无法找到文件
    private static final int SOURCE_FORBIDDEN = 405;// 资源被禁止
    private static final int NOT_ACCEPT = 406;//无法接受
    private static final int REQUEST_AGENT_ID = 407;// 请求代理身份
    private static final int REQUEST_TIMEOUT = 408;
    private static final int URL_TOO_LONG = 414;// 请求 – URI 太长


    private static final int INTERNAL_SERVER_ERROR = 500;// 内部服务器错误
    private static final int BAD_GATEWAY = 502;// 网关错误
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static NetResponseThrowable handleException(Throwable e) {
        NetResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new NetResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case HTTP:
                    ex.message = "请求无效";
                    break;
                case SOURCE_FORBIDDEN:
                    ex.message = "资源被禁止";
                    break;
                case URL_TOO_LONG:
                    ex.message = "请求URI太长";
                    break;
                case UNAUTHORIZED:
                case NOT_ACCEPT:
                case REQUEST_AGENT_ID:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new NetResponseThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new NetResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new NetResponseThrowable(e, ERROR.NETWORK_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new NetResponseThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new NetResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new NetResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else {
            ex = new NetResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }


    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORK_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

    public static class NetResponseThrowable extends Exception {
        public int code;
        public String message;

        public NetResponseThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;

        }
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}
