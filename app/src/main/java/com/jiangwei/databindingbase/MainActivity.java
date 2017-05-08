package com.jiangwei.databindingbase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.jiangwei.databindingbase.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Person person;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        person = new Person("jiangwei", "China", "男");
        activityMainBinding.setVariable(BR.person, person);
        activityMainBinding.setVariable(BR.handler, new Handler());
        activityMainBinding.setTime(new Date());
        activityMainBinding.viewStub.getViewStub().inflate();
    }

    public class Handler {
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "onClicked", Toast.LENGTH_SHORT).show();
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            person.setName(s.toString());
            // BaseObservable
            // @Bindable
            // activityMainBinding.setVariable(BR.person, person);
        }

        public void afterTextChanged(Editable s) {
            person.setName(s.toString());
            // BaseObservable
            // @Bindable
            // activityMainBinding.setVariable(BR.person, person);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            person.setName(s.toString());
            // BaseObservable
            // @Bindable
            // activityMainBinding.setVariable(BR.person, person);
        }

        // 回调
        public void onClickedListener(Person p) {
            Toast.makeText(MainActivity.this, p.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
