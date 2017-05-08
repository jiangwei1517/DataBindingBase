package com.jiangwei.databindingbase;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: jiangwei18 on 17/5/8 11:42 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class Utils {
    @BindingAdapter({ "bind:bindUri", "bind:placeHolder" })
    public static void loadImageFromUrl(ImageView iv, String bindUri, Drawable placeHolder) {
        Glide.with(iv.getContext()).load(bindUri).placeholder(placeHolder).into(iv);
    }

    @BindingConversion()
    public static String convertTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    @BindingConversion()
    public static String convertPerson(Person person) {
        Person p =new Person("zhouwenkai", "china", "man");
        return p.name;
    }
}
