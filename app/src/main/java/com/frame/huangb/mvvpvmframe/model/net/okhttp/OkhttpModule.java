package com.frame.huangb.mvvpvmframe.model.net.okhttp;

import com.frame.huangb.mvvpvmframe.model.net.OkhttpRequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取网络的dagger2 注入对象
 * Created by HuangBing on 2016/11/27.
 */
@Module
public class OkhttpModule {
    //网络baseUrl地址
    private String baseUrl;

    //网络超时时间
    private final short TIMEOUT = 15;

    private final String GSONTIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public OkhttpModule(String serviceUrl) {
        this.baseUrl = serviceUrl;
    }


//    @Provides
//    @Singleton
//    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson){
//        return new Retrofit.Builder()
//                .baseUrl(DribbleApi.END_POINT)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(okHttpClient)
//                .build();
//    }

    @Provides
    @Singleton
    public Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer 3e5943eddccdb8521ea0055413b56ed6").build());
            }
        }).addInterceptor(httpLoggingInterceptor).retryOnConnectionFailure(true)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(GSONTIMEFORMAT).create();
    }

    @Provides
    @Singleton
    public OkhttpRequestApi getOkhttpRequestApi(Retrofit retrofit) {
        return retrofit.create(OkhttpRequestApi.class);
    }

    @Provides
    public OkhttpRequestManager getOkhttpRequestManager(OkhttpRequestApi okhttpRequestApi) {
        return new OkhttpRequestManager(okhttpRequestApi);
    }

}
