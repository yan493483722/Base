package com.yan.mvp;

/**
 * Created by YanZi on 2017/6/16.
 * describe：
 * modify:
 * modify date:
 */
public class BaseResponse<T> {


    /**
     * token : zS0uN96jsU63pods7YW2pfCtXUi52cdcQQp5wRPCE4qR7Yw6tXzDlJHu25Jwf-9CqFx-mUkuk8kullnWdfek_BkCmh-pAx47jmgS1DA-PRegjM9e3ph3d6HW2wi91PfcblQusAJjxY48eKSqXSMq26bXchYDtpwSS6_em5WMpTVLctT67IAM73uIngqTaNMARup44ONbgg5Z09OK0Ylv5B5rVqWLXtdX5icZkWWSF58DYVuRUFrvw9McUGo7dg8-3YCvKyBU11S05rJ64gDczK1vpTi4Q7EHlgP8mZjf6tZn9Yiut-UbmXDKzmmUPEmzoWjCukDNOpIv27tcsy99QA0AaglkTpqL6VS7TTryu6ZIIBOW6A62IcjOZmnyJZV2vdBZF-TxbdZzWdTDCo7PpQklgw8zU-MPZUeVj-W6HHXvS6KlcG2u9jDElYL1g3Pb_6USXdXzWWFNdkCBDg5dAQ
     * positionid : 3
     */


    // 不传递类型 则默认 会返回 LinkedTreeMap
    private T result;
    /**
     * result : {"token":"zS0uN96jsU63pods7YW2pfCtXUi52cdcQQp5wRPCE4qR7Yw6tXzDlJHu25Jwf-9CqFx-mUkuk8kullnWdfek_BkCmh-pAx47jmgS1DA-PRegjM9e3ph3d6HW2wi91PfcblQusAJjxY48eKSqXSMq26bXchYDtpwSS6_em5WMpTVLctT67IAM73uIngqTaNMARup44ONbgg5Z09OK0Ylv5B5rVqWLXtdX5icZkWWSF58DYVuRUFrvw9McUGo7dg8-3YCvKyBU11S05rJ64gDczK1vpTi4Q7EHlgP8mZjf6tZn9Yiut-UbmXDKzmmUPEmzoWjCukDNOpIv27tcsy99QA0AaglkTpqL6VS7TTryu6ZIIBOW6A62IcjOZmnyJZV2vdBZF-TxbdZzWdTDCo7PpQklgw8zU-MPZUeVj-W6HHXvS6KlcG2u9jDElYL1g3Pb_6USXdXzWWFNdkCBDg5dAQ","positionid":3}
     * targetUrl : null
     * success : true
     * error : null
     * unAuthorizedRequest : false
     * __abp : true
     */

    private String targetUrl;
    private boolean success;
    private String error;
    private boolean unAuthorizedRequest;
    private boolean __abp;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Object getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isUnAuthorizedRequest() {
        return unAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(boolean unAuthorizedRequest) {
        this.unAuthorizedRequest = unAuthorizedRequest;
    }

    public boolean is__abp() {
        return __abp;
    }

    public void set__abp(boolean __abp) {
        this.__abp = __abp;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result=" + result +
                ", targetUrl='" + targetUrl + '\'' +
                ", success=" + success +
                ", error='" + error + '\'' +
                ", unAuthorizedRequest=" + unAuthorizedRequest +
                ", __abp=" + __abp +
                '}';
    }
}
