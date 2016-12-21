package com.frame.huangb.mvvpvmframe.model.net;

import com.frame.huangb.mvvpvmframe.FrameApplication;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.ApiResponseBean;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.IOkhttp;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpCallbackImp;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpRequestApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 请求接口管理
 * Created by HuangBing on 2016/11/27.
 */
public class OkhttpRequestManager {
    OkhttpRequestApi okhttpRequestApi;

    public OkhttpRequestManager(){

    }


    @Inject
    public OkhttpRequestManager(OkhttpRequestApi okhttpRequestApi) {
        this.okhttpRequestApi = okhttpRequestApi;
    }

    public void getHealthData() {
        System.out.println("============================getData");
        okhttpRequestApi.getBongConfig(1014543).enqueue(new OkhttpCallbackImp(new IOkhttp<String, ApiResponseBean>() {
            @Override
            public String requestSuccess(ApiResponseBean value) {
                System.out.println("============================requestSuccess");
                return null;
            }

            @Override
            public String requestFail(Object value) {
                System.out.println("================"+value.toString());
                return null;
            }
        }));
    }

    public int compareTo(String test){

        return 1;
    }
}
