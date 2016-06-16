package com.godot.stringup;

import android.util.Log;

/**
 * Created by Administrator on 2016/3/31.
 */
public class ResultChecker {
    private static final String TAG = "ResultChecker";

    static public boolean isRight(String s) {
        return isRightStringOneByOne(StringGenerator.getCurrentString(), s);
    }

    public static boolean isRightStringOneByOne(String currentString, String input) {
        /*get the currentString's last word's pinyin*/
        String lastWord = currentString.substring(currentString.length()-1, currentString.length());
        Log.d(TAG, "isRightStringOneByOne: lastWord=" + lastWord);
        String lastWordPY = Pinyin.getPyOfWord(lastWord);
        Log.d(TAG, "isRightStringOneByOne: lastWordPY=" + lastWordPY);

        /*get the input's first word's pinyin*/
        String firstWord = input.substring(0, 1);
        Log.d(TAG, "isRightStringOneByOne: firstWord=" + firstWord);
        String firstWordPY = Pinyin.getPyOfWord(input.substring(0, 1));
        Log.d(TAG, "isRightStringOneByOne: firstWordPY=" + firstWordPY);

        /*if the currentString's last word's pinyin is equal to the input's first word's pinyin, return true*/
        return firstWordPY.equals(lastWordPY);
    }
}
