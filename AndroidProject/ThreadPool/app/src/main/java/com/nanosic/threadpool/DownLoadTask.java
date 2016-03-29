package com.nanosic.threadpool;

import android.util.Log;

/**
 * Created by Administrator on 2016/3/29.
 */
public class DownLoadTask implements Runnable {
    private final String TAG = "DownLoadTask";

    private String mName;

    public DownLoadTask(String name) {
        this.mName = name;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        Log.d(TAG, "run: " + name + ", getFiledId=" + getFiledId());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getFiledId() {
        return this.mName;
    }
}
