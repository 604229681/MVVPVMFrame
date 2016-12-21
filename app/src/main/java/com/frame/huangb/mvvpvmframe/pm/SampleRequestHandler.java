package com.frame.huangb.mvvpvmframe.pm;

import com.frame.huangb.mvvpvmframe.FrameApplication;
import com.frame.huangb.mvvpvmframe.model.net.OkhttpRequestManager;

import javax.inject.Inject;

/**
 * 网络管理
 * Created by HuangBing on 2016/12/3.
 */
public class SampleRequestHandler {

    @Inject
    OkhttpRequestManager okhttpRequestManager;

    public SampleRequestHandler(){
        FrameApplication.appComponent.inject(this);
    }

    /**
     * 请求网络数据
     */
    public void requestBongData(){
        okhttpRequestManager.getHealthData();
    }

}
