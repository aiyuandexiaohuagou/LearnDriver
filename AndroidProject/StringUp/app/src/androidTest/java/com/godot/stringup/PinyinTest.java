package com.godot.stringup;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/1.
 */
public class PinyinTest extends InstrumentationTestCase{
    private static final String TAG = "PinyinTest";
    private DBController dbc;


    public void test_getPyOfWord() {
        Log.d(TAG, "test_getPyOfWord: Pinyin.getPyOfWord)=" + Pinyin.getPyOfWord("好"));
    }
}
