package com.frame.huangb.mvvpvmframe.model.net.okhttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求回调
 * Created by HuangBing on 2016/11/27.
 */
public class OkhttpCallbackImp<T,K> implements Callback<ApiResponseBean<K>> {
    IOkhttp iOkhttp;

    public OkhttpCallbackImp(IOkhttp<T,K> iOkhttp){
        this.iOkhttp = iOkhttp;
    }

    @Override
    public void onResponse(Call<ApiResponseBean<K>> call, Response<ApiResponseBean<K>> response) {
        System.out.println("============================onResponse");
        if(iOkhttp != null){
            if(response.code() == 200) {
                ApiResponseBean data = (ApiResponseBean) response.body();
                iOkhttp.requestSuccess(data.data);
            }else{
                iOkhttp.requestFail(response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<ApiResponseBean<K>> call, Throwable t) {
        System.out.println("============================onFailure");
        if(iOkhttp != null){
            iOkhttp.requestFail(t.getMessage());
        }
    }
}
