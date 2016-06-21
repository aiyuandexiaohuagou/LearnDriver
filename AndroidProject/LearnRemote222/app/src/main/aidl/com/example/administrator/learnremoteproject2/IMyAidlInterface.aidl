// IMyAidlInterface.aidl
package com.example.administrator.learnremoteproject2;

// Declare any non-default types here with import statements
import com.example.administrator.learnremoteproject2.MyRect;
import com.example.administrator.learnremoteproject2.IActivityCallback;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
/*    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/

    void setName(String name);
    String getName();
    void setRect(in MyRect e);
    MyRect getRect();

    void registerCallback(in IActivityCallback cb);
    void unRegisterCallback(in IActivityCallback cb);

}
