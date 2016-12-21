package com.frame.huangb.mvvpvmframe.commons.adapter;

import android.content.Context;

import com.frame.huangb.mvvpvmframe.R;
import com.frame.huangb.mvvpvmframe.commons.bean.MainBean;

import java.util.List;

/**
 *
 * Created by HuangBing on 2016/12/21.
 */
public class LoginRecycle extends BaseRecycleAdapter<MainBean> {

    public LoginRecycle(Context context, int layoutId,List<MainBean> beans) {
        super(context,layoutId, beans);
    }

    @Override
    protected void onBindDataToView(SolidCommonViewHolder holder, MainBean bean) {
        holder.setText(R.id.tv_code, bean.getTitleCode());
        holder.setText(R.id.tv_phone,bean.getTitleName());
    }

}
