package com.godot.stringup;

import android.test.InstrumentationTestCase;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ResultCheckerTest extends InstrumentationTestCase{
    public void test_isRightStringOneByOne() {
        String a = "三心二意";
        String b = "意气风发";

        assertTrue(ResultChecker.isRightStringOneByOne(a, b));
    }

}
