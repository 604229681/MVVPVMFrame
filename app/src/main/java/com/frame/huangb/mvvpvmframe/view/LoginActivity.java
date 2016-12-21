package com.frame.huangb.mvvpvmframe.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.frame.huangb.mvvpvmframe.R;
import com.frame.huangb.mvvpvmframe.commons.adapter.LoginRecycle;
import com.frame.huangb.mvvpvmframe.databinding.ActivityMainBinding;
import com.frame.huangb.mvvpvmframe.pv.IMainV;
import com.frame.huangb.mvvpvmframe.pv.MainPV;

public class LoginActivity extends Activity implements IMainV {
    private static final String TAG = "LoginActivity";

    private MainPV mainPV;

    private  ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPV = new MainPV(this);
        binding.setMainPV(mainPV);
        initAdapter();
    }

    private void initAdapter(){
        LoginRecycle loginRecycle = new LoginRecycle(this,R.layout.adapter_login_layout,mainPV.getMainBeans());
        binding.recyUser.setAdapter(loginRecycle);
        binding.recyUser.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showToast(final String text) {
        Log.i(TAG, "showToast: ============"+text);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this.getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }
        });
    }
}
