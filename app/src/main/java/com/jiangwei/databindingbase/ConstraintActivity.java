package com.jiangwei.databindingbase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jiangwei.databindingbase.databinding.ActivityConstraintBinding;

/**
 * author: jiangwei18 on 17/5/8 13:35 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class ConstraintActivity extends AppCompatActivity {
    private ActivityConstraintBinding mActivityConstraintBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityConstraintBinding = DataBindingUtil.setContentView(this, R.layout.activity_constraint);
        mActivityConstraintBinding.setVariable(BR.person, new Person("ConstraintLayout", "China", "man"));
    }
}
