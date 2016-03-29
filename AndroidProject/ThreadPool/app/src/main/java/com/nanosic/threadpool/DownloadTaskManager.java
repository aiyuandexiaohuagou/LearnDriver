package com.nanosic.threadpool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2016/3/29.
 */
public class DownloadTaskManager {
    private static final String TAG = "DownloadTaskManager";

    private LinkedList<DownLoadTask> downLoadTasks;

    private Set<String> taskIdSet;

    private static DownloadTaskManager downloadTaskManager;

    private DownloadTaskManager() {
        downLoadTasks = new LinkedList<>();
        taskIdSet = new HashSet<>();
    }

    public static synchronized DownloadTaskManager getInstance() {
        if (downloadTaskManager == null) {
            downloadTaskManager = new DownloadTaskManager();
        }
        return downloadTaskManager;
    }

    public void addDownLoadTask(DownLoadTask downLoadTask) {
        synchronized (downLoadTasks) {
            if (!isTaskRepeat(downLoadTask.getFiledId())) {
               downLoadTasks.addLast(downLoadTask); 
            }
        }
    }

    private boolean isTaskRepeat(String filedId) {
        synchronized (taskIdSet) {
            if (taskIdSet.contains(filedId)) {
                return true;
            } else {
                taskIdSet.add(filedId);
                return false;
            }
        }
    }

    public DownLoadTask getDownLoadTask() {
        synchronized (downLoadTasks) {
            if (downLoadTasks.size() > 0) {
                DownLoadTask downloadTask = downLoadTasks.removeFirst();
                return downloadTask;
            }
        }

        return null;
    }

}
