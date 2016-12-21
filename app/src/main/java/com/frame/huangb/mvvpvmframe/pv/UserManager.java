package com.frame.huangb.mvvpvmframe.pv;

/**
 * Created by HuangBing on 2016/12/15.
 */

public class UserManager {

    public void checkUserName(String name){
        if(name != null && name.length() != 0){
            name += "测试name";
        }
    }
}
