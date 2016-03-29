
package com.nanosic.threadpool;

import android.content.res.Resources;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/29.
 */
public class DownLoadTaskManagerThread implements Runnable {
    private String TAG = "DownLoadTaskManagerThread";

    private DownloadTaskManager downloadTaskManager;

    private ExecutorService pool;

    private final int POOL_SIZE = 5;

    private final int SLEEP_TIME = 1000;

    private boolean isStop = false;

    DownLoadTaskManagerThread() {
        downloadTaskManager = DownloadTaskManager.getInstance();
        pool = Executors.newFixedThreadPool(POOL_SIZE);
    }

    @Override
    public void run() {
        while (!isStop) {
            DownLoadTask downLoadTask = downloadTaskManager.getDownLoadTask();
            if (downLoadTask != null) {
                Log.d(TAG, "run: downLoadTask.getFiledId()=" + downLoadTask.getFiledId() + ", pool.execute(downLoadTask)");
                pool.execute(downLoadTask);
            } else {
                try {
                    Log.d(TAG, "run: There is no downLoadTask in DownloadTaskManager");
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (isStop) {
            pool.shutdown();
        }
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }
}
