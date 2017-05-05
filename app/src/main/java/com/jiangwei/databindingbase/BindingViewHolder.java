package com.jiangwei.databindingbase;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * author: jiangwei18 on 17/5/5 18:51 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
