package com.frame.huangb.mvvpvmframe.commons.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by HuangBing on 2016/12/21.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleAdapter.SolidCommonViewHolder> {
    protected List<T> mBeans;
    protected Context mContext;
    protected boolean mAnimateItems = true;
    protected int mLastAnimatedPosition = -1;
    private int layoutId;

    public BaseRecycleAdapter(Context context,int layoutId, List<T> beans) {
        mContext = context;
        this.layoutId = layoutId;
        mBeans = beans;
    }

    @Override
    public BaseRecycleAdapter.SolidCommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutId, parent, false);
        SolidCommonViewHolder holder = new SolidCommonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecycleAdapter.SolidCommonViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        onBindDataToView(holder, mBeans.get(position));
    }

    /**
     * 绑定数据到Item的控件中去
     *
     * @param holder
     * @param bean
     */
    protected abstract void onBindDataToView(SolidCommonViewHolder holder, T bean);

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void add(T bean) {
        mBeans.add(bean);
        notifyDataSetChanged();
    }

    public void addAll(List<T> beans) {
        mBeans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clear() {
        mBeans.clear();
        notifyDataSetChanged();
    }

    /**
     * item的加载动画
     *
     * @param view
     * @param position
     */
    private void runEnterAnimation(View view, int position) {
        if (!mAnimateItems) {
            return;
        }
        if (position > mLastAnimatedPosition) {
            mLastAnimatedPosition = position;
            view.animate()
                    .translationY(50)
                    .setStartDelay(100)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(300)
                    .start();
        }
    }

    public class SolidCommonViewHolder extends
            RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;
        private View itemView;

        public SolidCommonViewHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
            this.itemView = itemView;
            //添加Item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(getAdapterPosition());
                }
            });
        }

        public <D extends View> D getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (D) view;
        }

        public void setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }

        /**
         * 加载drawable中的图片
         *
         * @param viewId
         * @param resId
         */
        public void setImage(int viewId, int resId) {
            ImageView iv = getView(viewId);
            iv.setImageResource(resId);
        }
    }

    /**
     * ItemView的单击事件(如果需要，重写此方法就行)
     *
     * @param position
     */
    protected void onItemClick(int position) {
    }

}
