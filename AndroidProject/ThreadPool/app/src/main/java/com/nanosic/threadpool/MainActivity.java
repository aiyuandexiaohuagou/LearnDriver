package com.nanosic.threadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTaskManager.getInstance();

        DownLoadTaskManagerThread downLoadTaskManagerThread = new DownLoadTaskManagerThread();
        new Thread(downLoadTaskManagerThread).start();

        String[] items = new String[] {"down1", "down2", "down3", "down4", "down5", "down6", "down7", "down1"};
        for (String item : items) {
            DownloadTaskManager downloadTaskManager = DownloadTaskManager.getInstance();
            Log.d(TAG, "onCreate: addDownLoadTask(" + item + ")");
            downloadTaskManager.addDownLoadTask(new DownLoadTask(item));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
