package com.godot.stringup;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/1.
 */
public class Pinyin {
    private static final String TAG = "Pinyin";

    public static String getPyOfWord(String input) {
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance(true).get(input);
        Log.d(TAG, "getPyOfWord: tokens=" + tokens);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
