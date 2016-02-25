package com.example.administrator.learnremoteproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/2/24.
 */
public final class MyRect implements Parcelable {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public static final Parcelable.Creator<MyRect> CREATOR = new Parcelable.Creator<MyRect>() {
        public MyRect createFromParcel(Parcel in) {
            return new MyRect(in);
        }

        public MyRect[] newArray(int size) {
            return new MyRect[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(left);
        dest.writeInt(top);
        dest.writeInt(right);
        dest.writeInt(bottom);
    }


    public void readFromParcel(Parcel in) {
        left = in.readInt();
        top = in.readInt();
        right = in.readInt();
        bottom = in.readInt();
    }

    public MyRect() {
    }

    public MyRect(int l, int t, int r, int b) {
        left = l;
        top = t;
        right = r;
        bottom = b;
    }

    public MyRect(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
