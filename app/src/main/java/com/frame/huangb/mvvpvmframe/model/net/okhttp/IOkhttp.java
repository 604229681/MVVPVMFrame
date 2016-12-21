package com.frame.huangb.mvvpvmframe.model.net.okhttp;

/**
 * 网络请求接口
 * Created by HuangBing on 2016/11/27.
 */
public interface IOkhttp<T,K> {
    T  requestSuccess(K value);

    T requestFail(Object value);
}
