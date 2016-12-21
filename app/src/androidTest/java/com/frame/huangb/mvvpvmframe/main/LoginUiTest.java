package com.frame.huangb.mvvpvmframe.main;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.frame.huangb.mvvpvmframe.R;
import com.frame.huangb.mvvpvmframe.view.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by HuangBing on 2016/12/14.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginUiTest {
    //规则
    @Rule
    public ActivityTestRule<LoginActivity> loginActivity = new ActivityTestRule(LoginActivity.class);

    @Test
    public void test_mobile() throws Exception {
        int phone = 0;
        while (true) {
            //根据id找到控件,并判断是否可见
            ViewInteraction viewInteraction = onView(allOf(withId(R.id.et_mobile), isDisplayed()));
            //执行替换文本操作,说白了就是输入文本,输入完毕之后关闭输入法键盘
            viewInteraction.perform(replaceText("1537243991" + (++phone)), closeSoftKeyboard());
            onView(withId(R.id.et_mobile)).check(matches(withText("1537243991" + (phone))));


            //根据id找到控件,并判断是否可见
            ViewInteraction viewCode = onView(allOf(withId(R.id.et_code), isDisplayed()));
            //执行替换文本操作,说白了就是输入文本,输入完毕之后关闭输入法键盘
            viewCode.perform(replaceText("12345" + (++phone)), closeSoftKeyboard());
            onView(withId(R.id.et_code)).check(matches(withText("12345" + (phone))));

            test_loginButton();
            //test_withText();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_withText() throws Exception {
        //根据文本找到"Hello Espresso!"按钮,并判断是否可见
        ViewInteraction appCompatButton = onView(
                allOf(withText("Hello Espresso!"), isDisplayed()));
        appCompatButton.perform(replaceText("kkkkkkkkkkkkkkkkkkk"), closeSoftKeyboard());
    }

    @Test
    public void test_loginButton() throws Exception {
        onView(withId(R.id.btn)).perform(click());//执行点击button
    }

    @Test
    public void test_recycleView() throws Exception{
        //判断recycleView是否显示
        onView(allOf(withId(R.id.recy_user),isDisplayed()));

        //点击0位置的view
        onView(allOf(withId(R.id.recy_user),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //找到RecycleView里面文字为Hello的item并点击
        onView(withId(R.id.tv_code)) .perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("Hello")), click()));

    }
}

