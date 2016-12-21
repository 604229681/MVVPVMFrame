package com.frame.huangb.mvvpvmframe.model.net.okhttp;

/**
 * Created by HuangBing on 2016/11/27.
 */

public class ApiResponseBean<T> {
    public int code;
    public String msg;
    public T data;

    public ApiResponseBean() {
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
}
