package com.example.administrator.learnremoteproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/2/23.
 */
public class MyRemoteService extends Service {
    final private String TAG = "MyRemoteService";
    private RemoteCallbackList<IActivityCallback> mCbList = new RemoteCallbackList<IActivityCallback>() {
        @Override
        public void onCallbackDied(IActivityCallback callback) {
            super.onCallbackDied(callback);
            Log.e(TAG, "onCallbackDied");
        }
    };
    private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        private String mName;
         int count = 0;
        private MyRect mR = new MyRect();

        @Override
        public void registerCallback(IActivityCallback cb) throws RemoteException {
            mCbList.register(cb);
            Log.d(TAG, "registerCallback");
        }

        @Override
        public void unRegisterCallback(IActivityCallback cb) throws RemoteException {
            mCbList.unregister(cb);
            Log.d(TAG, "unRegisterCallback");
        }

        @Override
        public void setRect(MyRect e) throws RemoteException {
            mR = e;
            Log.d(TAG, String.format("setRect(%d, %d, %d, %d)", mR.left, mR.top, mR.right, mR.bottom));
            callback(e);
        }

        @Override
        public MyRect getRect() throws RemoteException {
            return mR;
        }

        @Override
        public void setName(String name) throws RemoteException {
            mName = name;
        }

        @Override
        public String getName() throws RemoteException {
            Log.d(TAG, "ProcessId=" + android.os.Process.myPid());
            count++;
            return (mName + "-" + count);
        }
    };

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    private void callback(MyRect r) throws RemoteException {
        int n = mCbList.beginBroadcast();
        Log.d(TAG, "n="+n);
        for (int i=0; i<n; i++) {
            mCbList.getBroadcastItem(i).onNotifySet(r);
        }
        mCbList.finishBroadcast();
    }
}
