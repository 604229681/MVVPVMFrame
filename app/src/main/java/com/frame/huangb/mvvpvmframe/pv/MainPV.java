package com.frame.huangb.mvvpvmframe.pv;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.frame.huangb.mvvpvmframe.commons.adapter.LoginRecycle;
import com.frame.huangb.mvvpvmframe.commons.bean.MainBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 主界面逻辑逻辑控制
 * Created by HuangBing on 2016/12/13.
 */
public class MainPV {
    private static final String TAG = "MainPV";
    
    private IMainV iMainV;

    public ObservableField<String> mobilesEt = new ObservableField<>();

    public ObservableField<String> verificationCodeEt = new ObservableField<>();

    public MainPV(IMainV iMainV) {
        this.iMainV = iMainV;
    }

    /**
     * 点击登录
     */
    public void onClickLogin(View v) {
        try {
            Log.i(TAG, "onClickLogin: ");
            checkMobileAndCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkMobileAndCode() throws Exception {
        Log.i(TAG, "checkMobileAndCode: "+mobilesEt.get()+"==="+verificationCodeEt.get());
        if (!isMobilesNum(mobilesEt.get())) {
            if (iMainV != null)
                iMainV.showToast("电话号码错误...");
        } else if (isVerificationCode(verificationCodeEt.get())) {
            if (iMainV != null)
                iMainV.showToast("验证码错误...");
        }

    }

    /**
     * 检查是否是电话号码
     *
     * @param mobiles
     * @return
     */
    public boolean isMobilesNum(String mobiles) throws Exception {
        if(mobiles == null || mobiles.length() ==0)
            return false;
        Log.i(TAG, "isMobilesNum: ");
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        return p.matcher(mobiles).matches();
    }

    /**
     * 判断是否是6位验证码
     *
     * @param code
     * @return
     */
    public boolean isVerificationCode(String code) throws Exception {
        if(code == null || code.length() ==0)
            return false;
        Log.i(TAG, "isVerificationCode: "+code);
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(code).matches() & code.length() == 6;
    }

    public List<MainBean> getMainBeans(){
        List<MainBean> beans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MainBean mainBean= new MainBean();
            beans.add(mainBean);
        }

        return beans;
    }

    private UserManager userManager = new UserManager();

    public void checkName(){
        String name = "123";
        userManager.checkUserName(name);
    }

    public void setUserManager( UserManager userManager){
        this.userManager=userManager;
    }

}
