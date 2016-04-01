package com.nanosic.stringup;

import java.util.Currency;

/**
 * Created by Administrator on 2016/3/31.
 */
public class StringGenerator {
    static private String currentString;
    static private DBController dbController;

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
            return null;
        }

        /*test*/






        /********************************************/

        String ret = null;
        /*get the last word and it's pinyin*/
        String lastWord = s.substring(s.length()-1, s.length());
        String lastWordPinyin = Pinyin.getPyOfWord(lastWord);

        /*if can find string which head is the last word, first get it*/
        ret = getStringByFirstWord(lastWord);
        if (ret != null) {
            return ret;
        }

        /*else, get the last word's pinyin, find the similar world*/
        ret = getStringByFirstWordPinyin(lastWordPinyin);

        return ret;
    }

    private static String getStringByFirstWordPinyin(String firstWordPinyin) {
        return null;
    }

    private static String getStringByFirstWord(String firstWord) {
        return null;
    }

    static public String getCurrentString() {
        return currentString;
    }

}
