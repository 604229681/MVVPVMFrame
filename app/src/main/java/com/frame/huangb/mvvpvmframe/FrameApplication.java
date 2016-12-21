package com.frame.huangb.mvvpvmframe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.frame.huangb.mvvpvmframe.commons.AppComponent;
import com.frame.huangb.mvvpvmframe.commons.DaggerAppComponent;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpModule;

/**
 * 框架的app
 * Created by HuangBing on 2016/11/27.
 */
public class FrameApplication extends Application {
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getDaggerAppComponent();
    }

    /**
     * 获取dagger 对象
     *
     * @return
     */
    public static void getDaggerAppComponent() {
        appComponent =  DaggerAppComponent.builder().okhttpModule(new OkhttpModule("https://beta.halove.com")).build();
    }

    public static void setAppComponent(AppComponent component){
        appComponent = component;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            MultiDex.install(this);
        } catch (RuntimeException multiDexException) {
            // Work around Robolectric causing multi dex installation to fail, see
            // https://code.google.com/p/android/issues/detail?id=82007.
            boolean isUnderUnitTest;

            try {
                Class<?> robolectric = Class.forName("org.robolectric.Robolectric");
                isUnderUnitTest = (robolectric != null);
            } catch (ClassNotFoundException e) {
                isUnderUnitTest = false;
            }

            if (!isUnderUnitTest) {
                // Re-throw if this does not seem to be triggered by Robolectric.
                throw multiDexException;
            }
        }
    }
}
