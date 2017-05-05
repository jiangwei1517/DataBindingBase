package com.jiangwei.databindingbase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jiangwei.databindingbase.databinding.ActivityRecycleBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * author:  jiangwei18 on 17/5/5 18:42
 * email:  jiangwei18@baidu.com
 * Hi:   jwill金牛
 */

public class RecycleViewActivity extends AppCompatActivity {
    private ActivityRecycleBinding mbinding;
    private PersonAdapter mPersonAdapter;
    private List<Person> lists;

    public class Presenter {
        public void add(View v) {
            mPersonAdapter.add(new Person("周文凯", "China", "man"));
        }

        public void remove(View v) {
            mPersonAdapter.remove();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_recycle);
        mbinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mbinding.setVariable(BR.presenter, new Presenter());
        lists = new ArrayList<>();
        lists.add(new Person("姜威", "China", "man"));
        lists.add(new Person("周文凯", "Japanese", "man"));
        lists.add(new Person("姜威", "China", "man"));
        lists.add(new Person("周文凯", "Japanese", "man"));
        lists.add(new Person("姜威", "China", "man"));
        lists.add(new Person("周文凯", "Japanese", "man"));
        lists.add(new Person("姜威", "China", "man"));
        lists.add(new Person("周文凯", "Japanese", "man"));
        lists.add(new Person("姜威", "China", "man"));
        lists.add(new Person("周文凯", "Japanese", "man"));
        mPersonAdapter = new PersonAdapter(this);
        mPersonAdapter.addAll(lists);
        mPersonAdapter.setmListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onClick(Person person) {
                Toast.makeText(RecycleViewActivity.this, person.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mbinding.recycleView.setAdapter(mPersonAdapter);
    }
}
