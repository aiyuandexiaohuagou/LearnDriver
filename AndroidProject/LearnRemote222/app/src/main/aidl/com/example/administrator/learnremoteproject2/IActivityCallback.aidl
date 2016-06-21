// IActivityCallback.aidl
package com.example.administrator.learnremoteproject2;

// Declare any non-default types here with import statements
import com.example.administrator.learnremoteproject2.MyRect;

interface IActivityCallback {
    void onNotifySet(in MyRect r);
}
