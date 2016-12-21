package com.frame.huangb.mvvpvmframe.commons;

import com.frame.huangb.mvvpvmframe.pm.SampleRequestHandler;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 数据组件
 * Created by HuangBing on 2016/11/27.
 */
@Singleton
@Component(modules = {OkhttpModule.class})
public interface AppComponent {
    void inject(SampleRequestHandler sampleRequestHandler);
}
