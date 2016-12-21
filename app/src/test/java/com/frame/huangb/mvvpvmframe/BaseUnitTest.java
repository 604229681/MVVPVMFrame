package com.frame.huangb.mvvpvmframe;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;

import java.net.URISyntaxException;

/**
 * 单元测试基类
 * Created by HuangBing on 2016/12/13.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class BaseUnitTest {
    public Context mContext;
    public ShadowApplication shadowApplication;

    @Before
    public void setUp() throws URISyntaxException {
        //输出日志
        ShadowLog.stream = System.out;
        //初始化mock
        MockitoAnnotations.initMocks(this);

        mContext = RuntimeEnvironment.application;

        shadowApplication = Shadows.shadowOf((Application)mContext);

    }
}
