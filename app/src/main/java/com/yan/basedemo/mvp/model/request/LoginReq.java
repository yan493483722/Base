package com.yan.basedemo.mvp.model.request;

/**
 * Created by YanZi on 2017/8/15.
 * describe：
 * modify:
 * modify date:
 */
public class LoginReq {


    private String usernameOrEmailAddress;

    private String validateCode;

    private String password;

    private boolean rememberMe;

    public String getUsernameOrEmailAddress() {
        return usernameOrEmailAddress;
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "usernameOrEmailAddress='" + usernameOrEmailAddress + '\'' +
                ", validateCode='" + validateCode + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
