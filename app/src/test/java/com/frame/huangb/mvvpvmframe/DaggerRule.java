package com.frame.huangb.mvvpvmframe;

import com.frame.huangb.mvvpvmframe.commons.AppComponent;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by HuangBing on 2016/12/3.
 */
public class DaggerRule  extends DaggerMockRule<AppComponent> {
    public DaggerRule() {
        //告诉DaggerMock要build什么样的Component，使用哪个module
        super(AppComponent.class, new OkhttpModule("https://beta.halove.com"));

        //告诉DaggerMock把build好的Component放到哪
        set(new ComponentSetter<AppComponent>() {
            @Override
            public void setComponent(AppComponent appComponent) {
                FrameApplication.setAppComponent(appComponent);
            }
        });
    }
}