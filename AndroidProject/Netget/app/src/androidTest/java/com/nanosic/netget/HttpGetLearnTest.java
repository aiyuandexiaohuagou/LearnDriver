package com.nanosic.netget;

import android.test.InstrumentationTestCase;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/30.
 */
public class HttpGetLearnTest extends InstrumentationTestCase {
    public void test_requestByGet() {
        try {
            HttpGetLearn.requestByGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test_requestByPost() {
        try {
            HttpGetLearn.requestByPost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
