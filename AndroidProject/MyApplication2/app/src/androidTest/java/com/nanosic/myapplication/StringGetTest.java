package com.nanosic.myapplication;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by hujihao on 16/3/31.
 */
public class StringGetTest extends InstrumentationTestCase{
    private String TAG = "StringGetTest";
    public void test_a() {
        String ret = StringGet.getLastFont("兴高采烈");
        Log.d(TAG, "test_a: ret="+ret);
    }
}
