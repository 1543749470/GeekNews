package com.jy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class HttpUtils {
    public static boolean isNetworkAvailable(Context context){
        if (context!=null){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info!=null){
                return info.isAvailable();
            }

        }
        return false;
    }
}
