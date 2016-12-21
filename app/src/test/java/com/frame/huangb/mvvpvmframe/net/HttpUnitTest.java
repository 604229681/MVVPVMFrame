package com.frame.huangb.mvvpvmframe.net;

import android.util.Log;
import android.widget.Button;

import com.frame.huangb.mvvpvmframe.BaseUnitTest;
import com.frame.huangb.mvvpvmframe.FrameApplication;
import com.frame.huangb.mvvpvmframe.R;
import com.frame.huangb.mvvpvmframe.commons.AppComponent;
import com.frame.huangb.mvvpvmframe.commons.DaggerAppComponent;
import com.frame.huangb.mvvpvmframe.model.net.OkhttpRequestManager;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.ApiResponseBean;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.IOkhttp;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpCallbackImp;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpModule;
import com.frame.huangb.mvvpvmframe.model.net.okhttp.OkhttpRequestApi;
import com.frame.huangb.mvvpvmframe.pm.SampleRequestHandler;
import com.frame.huangb.mvvpvmframe.pv.MainPV;
import com.frame.huangb.mvvpvmframe.pv.UserManager;
import com.frame.huangb.mvvpvmframe.view.LoginActivity;
import com.google.gson.GsonBuilder;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HttpUnitTest extends BaseUnitTest {

//    @Rule
//    public DaggerRule daggerRule = new DaggerRule();

    @Mock
    OkhttpRequestManager okhttpRequestManager;


    @Test
    public void compareToTest() throws Exception{
        OkhttpRequestManager okhttpRequestManager = spy(OkhttpRequestManager.class);
        System.out.println("compareToTest："+okhttpRequestManager.compareTo("Test"));

        assertEquals(okhttpRequestManager.compareTo("Test"),1);
    }

    @Test
    public void okhttpTest() throws Exception{
        //创建一个mockAppModule，这里不能spy(AppModule.class)，因为`AppModule`没有默认无参数的Constructor，也不能mock(AppModule.class),原因是dagger2的约束，Provider方法不能返回null，除非用@Nullable修饰
        OkhttpModule okhttpModule = spy(new OkhttpModule("https://beta.halove.com"));
        //OkhttpRequestManager okhttpRequestManager = mock(OkhttpRequestManager.class);
        Mockito.when(okhttpModule.getOkhttpRequestManager(any(OkhttpRequestApi.class))).thenReturn(okhttpRequestManager);  //当mockAppModule的provideLoginPresenter()方法被调用时，让它返回mockLoginPresenter
        AppComponent appComponent = DaggerAppComponent.builder().okhttpModule(okhttpModule).build();  //用mockAppModule来创建DaggerAppComponent
        FrameApplication.setAppComponent(appComponent);  //假设你的Component是放在FrameApplication里面的
        SampleRequestHandler requestHandler =  new SampleRequestHandler();
        requestHandler.requestBongData();
        verify(okhttpRequestManager).getHealthData();
    }

    @Test
    public void testMockData(){
        UserManager userManager = Mockito.mock(UserManager.class);
        MainPV mMainPV = new MainPV(null);
        mMainPV.setUserManager(userManager);
        mMainPV.checkName();
        Mockito.verify(userManager).checkUserName("123");//参数值必须和checkName 的name一直不一直就出错
        System.out.println("===============");
    }

    @Test
    public void testMainActivity() {
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        Button button = (Button) loginActivity.findViewById(R.id.btn);
        button.performClick();
        assertEquals( button.getText(),"Hello World!");
    }

    @Test
    public void  repositories() throws Exception{
        getOkhttpRequestApi().getBongConfig(123456).enqueue(new OkhttpCallbackImp(new IOkhttp<String, ApiResponseBean>() {
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

    public OkhttpRequestApi getOkhttpRequestApi() {
        return getRetrofit().create(OkhttpRequestApi.class);
    }

    public Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl("https://beta.halove.com").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create())).client(getOkHttpClient()).build();
    }

    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer 3e5943eddccdb8521ea0055413b56ed6").build());
            }
        }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

//
//    @Test
//    public void okhttpRuleTest() throws Exception{
//        //OkhttpRequestManager okhttpRequestManager = mock(OkhttpRequestManager.class);
//        SampleRequestHandler requestHandler =  new SampleRequestHandler();
//        requestHandler.requestBongData();
//        verify(okhttpRequestManager).getHealthData();
//    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

}