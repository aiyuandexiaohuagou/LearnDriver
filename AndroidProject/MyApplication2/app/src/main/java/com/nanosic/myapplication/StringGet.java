package com.nanosic.myapplication;

import android.util.Log;

/**
 * Created by hujihao on 16/3/31.
 */
public class StringGet {
    static private String TAG = "StringGet";
    static public String getLastFont(String a) {
        String b = a.substring(a.length() - 1, a.length());
        Log.d(TAG, "StringGet: b=" + b + ", a.length()="+a.length());

        return b;
    }
}
