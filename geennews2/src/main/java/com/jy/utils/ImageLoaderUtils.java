package com.jy.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jy.greendao.MyApp;

public class ImageLoaderUtils {

    public static void showImg(String url, ImageView img) {
        if (!Constants.isNoIMg) {
            Glide.with(MyApp.getInstance()).load(url).into(img);
        }else{
            img.setImageResource(0);
        }
    }
}
