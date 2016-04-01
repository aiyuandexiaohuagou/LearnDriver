package com.nanosic.stringup;

import java.util.Currency;

/**
 * Created by Administrator on 2016/3/31.
 */
public class StringGenerator {
    static private String currentString;

    /*
    * if s is null, generate a string randomly; else
    * generate a string according to the input s.
    *
    * */
    static public String generateAString(String s) {
        String result;
        result = generate(s);
        if (result != null) {
            currentString = result;
        }
        return result;
    }

    private static String generate(String s) {
        String ret = null;

        return ret;
    }

    static public String getCurrentString() {
        return currentString;
    }

}
