package com.frame.huangb.mvvpvmframe.model.net.okhttp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 请求的url api
 * Created by HuangBing on 2016/11/27.
 */
public interface OkhttpRequestApi {

    /**
     * 获取用户bong配置信息
     *
     * @return
     */
    @GET("/v1/health/config")
    Call<ApiResponseBean<String>> getBongConfig(@Header("userid") long userId);
}
