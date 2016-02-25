// IActivityCallback.aidl
package com.example.administrator.learnremoteproject;

// Declare any non-default types here with import statements
import com.example.administrator.learnremoteproject.MyRect;

interface IActivityCallback {
    void onNotifySet(in MyRect r);
}
