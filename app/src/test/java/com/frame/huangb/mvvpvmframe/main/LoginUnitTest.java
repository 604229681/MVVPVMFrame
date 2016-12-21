package com.frame.huangb.mvvpvmframe.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.frame.huangb.mvvpvmframe.BaseUnitTest;
import com.frame.huangb.mvvpvmframe.R;
import com.frame.huangb.mvvpvmframe.commons.bean.MainBean;
import com.frame.huangb.mvvpvmframe.pv.MainPV;
import com.frame.huangb.mvvpvmframe.view.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowTextView;
import org.robolectric.util.ActivityController;

import java.net.URISyntaxException;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * 测试登录界面
 * Created by HuangBing on 2016/12/13.
 */
@Config(shadows = ShadowsMainBean.class)
public class LoginUnitTest extends BaseUnitTest {
    private static final String TAG = "LoginUnitTest";
    private LoginActivity mLoginActivity;
    private MainPV mMainPV;
    private Button mButton;
    private EditText etCode;

    @Before
    public void setUp() throws URISyntaxException {
        super.setUp();
        mLoginActivity = Robolectric.setupActivity(LoginActivity.class);
        mMainPV = new MainPV(mLoginActivity);
        mButton = (Button) mLoginActivity.findViewById(R.id.btn);
        etCode = (EditText) mLoginActivity.findViewById(R.id.et_code);
    }

    /**
     * 测试activity是否为空
     */
    @Test
    public void test_Activity() {
        assertNotNull(mLoginActivity);
        assertEquals(mLoginActivity.getTitle(), "MVVPVMFrame");
    }

    /**
     * 测试shadows 获取的数据是真实对象，还是shadows对象的数据
     */
    @Test
    public void test_shadows() {
        MainBean bean = new MainBean();
        assertEquals("测试标题", bean.getTitleName());
        Log.i(TAG, "testActivity: ======" + bean.getTitleName() + "=========" + bean.getTitleCode());

        ShadowsMainBean shadowsMainBean = (ShadowsMainBean) ShadowExtractor.extract(bean);
        Log.i(TAG, "testActivity: ======" + shadowsMainBean.getTitleName() + "=========" + bean.getTitleCode());
        assertEquals("测试标题", shadowsMainBean.getTitleName());
    }

    /**
     * 测试shadowsActivity对象
     */
    @Test
    public void test_shadowsActivity() {
        //通过Shadows.shadowOf()可以获取很多Android对象的Shadow对象
        ShadowActivity shadowActivity = Shadows.shadowOf(mLoginActivity);
        //Shadow对象提供方便我们用于模拟业务场景进行测试的api
        assertNull(shadowActivity.getNextStartedActivity());
        assertNull(shadowApplication.getNextStartedActivity());
    }

    @Test
    public void test_shadowTextView() {
        etCode.setText("123456");
        ShadowTextView stv = Shadows.shadowOf(etCode);

        String innerText = stv.innerText();
        System.out.println("==============" + innerText + "===========" + etCode.getText());
    }


    @Test
    public void test_onClickLogin() throws Exception {
        mMainPV.mobilesEt.set("15372439911");
        mMainPV.verificationCodeEt.set("123456");
        mButton.performClick();

//        mMainPV.mobilesEt.set("15372439911");
//        mMainPV.verificationCodeEt.set("123456");
//        mMainPV.onClickLogin(mLoginActivity.findViewById(R.id.btn));
    }

    @Test
    public void test_checkMobileAndCode() throws Exception {
        mMainPV.mobilesEt.set("15372439911");
        mMainPV.verificationCodeEt.set("123456");
        mMainPV.checkMobileAndCode();
    }

    @Test
    public void test_isMobilesNum() throws Exception {
        assertTrue(mMainPV.isMobilesNum("15372439911"));
        //assertTrue(mMainPV.isMobilesNum("123456"));
    }

    @Test
    public void test_isVerificationCode() throws Exception {
        assertTrue(mMainPV.isVerificationCode("123456"));
        //assertTrue(mMainPV.isVerificationCode("123abd"));
    }

    /**
     * 检查一些在onCreate()和onResume()之间发生的事件,及生命周期
     */
    @Test
    public void test_createOrResume() throws Exception {
        ActivityController activityController = Robolectric.buildActivity(LoginActivity.class).create().start().resume();
        Activity activity = (Activity) activityController.get();
    }

    /**
     * 使用intent 来启动activity
     */
    @Test
    public void test_intentActivity() {
        Bundle savedInstanceState = new Bundle();
        savedInstanceState.putString("user_age", "18");
        savedInstanceState.putString("user_name", "jay");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Activity activity = Robolectric.buildActivity(LoginActivity.class).restoreInstanceState(savedInstanceState).withIntent(intent).create().get();
    }

}
