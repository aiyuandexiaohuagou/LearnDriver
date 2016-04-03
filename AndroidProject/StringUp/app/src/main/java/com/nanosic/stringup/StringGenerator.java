package com.nanosic.stringup;

import android.content.Context;
import android.util.Log;

import java.util.Currency;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/31.
 */
public class StringGenerator {
    private static final  String TAG = "StringGenerator";
    static private String currentString = "北道主人";
    static private DBController dbController;
    static private Context context;
    static private StringGenerator stringGenerator;

    static public StringGenerator getInstance(Context c) {
        if (stringGenerator == null) {
            stringGenerator = new StringGenerator(c);
        }
        return stringGenerator;
    }

    private StringGenerator(Context c) {
        context = c;
    }

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
        if (s == null) {
            return DBController.getInstance(context).findId(new Random().nextInt(Define.LineMax));
        }

        /*if can find string which head is the last word, first get it*/
        return getStringByFirstWord(s.substring(s.length() - 1, s.length()));
    }

    private static String getStringByFirstWordPinyin(String firstWordPinyin) {
        return null;
    }

    private static String getStringByFirstWord(String firstWord) {
        String ret = DBController.getInstance(context).find(firstWord);

        Log.d(TAG, "getStringByFirstWord: ret=" + ret);

        return ret;
    }

    static public String getCurrentString() {
        return currentString;
    }

}
