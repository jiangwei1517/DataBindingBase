package com.jiangwei.databindingbase;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * author: jiangwei18 on 17/5/5 14:52 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class Person extends BaseObservable {
    @Bindable
    public String name;
    @Bindable
    public String nation;
    @Bindable
    public String sex;

    public Person(String name, String nation, String sex) {
        this.name = name;
        this.nation = nation;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
        notifyPropertyChanged(BR.nation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }
}
