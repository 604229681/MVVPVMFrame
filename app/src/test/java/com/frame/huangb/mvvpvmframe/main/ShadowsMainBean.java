package com.frame.huangb.mvvpvmframe.main;

import com.frame.huangb.mvvpvmframe.commons.bean.MainBean;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * Created by HuangBing on 2016/12/14.
 */
@Implements(MainBean.class)
public class ShadowsMainBean {
    @Implementation
    public String getTitleName(){
        return "测试标题";
    }
    public String getTitleCode(){

        return "999999";
    }
}
