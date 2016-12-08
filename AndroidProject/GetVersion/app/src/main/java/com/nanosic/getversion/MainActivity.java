package com.nanosic.getversion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.txt_1);
        assert(textView != null);
        textView.setMovementMethod(new ScrollingMovementMethod());

        String info =
                "ID: " + android.os.Build.ID + "\n" +
                "DISPLAY: " + android.os.Build.DISPLAY + "\n" +
                "PRODUCT: " + android.os.Build.PRODUCT + "\n" +
                "DEVICE: " + android.os.Build.DEVICE + "\n" +
                "BOARD: " + android.os.Build.BOARD + "\n" +
                "CPU_ABI: " + android.os.Build.CPU_ABI + "\n" +
                "MANUFACTURER: " + android.os.Build.MANUFACTURER + "\n" +
                "BRAND: " + android.os.Build.BRAND + "\n" +
                "MODEL: " + android.os.Build.MODEL + "\n" +
                "BOOTLOADER: " + android.os.Build.BOOTLOADER + "\n" +
                "HARDWARE: " + android.os.Build.HARDWARE + "\n" +
                "VERSION.INCREMENTAL: " + android.os.Build.VERSION.INCREMENTAL + "\n" +
                "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + "\n" +
                "VERSION.SDK: " + android.os.Build.VERSION.SDK + "\n" +
                "VERSION.SDK_INT: " + android.os.Build.VERSION.SDK_INT + "\n" +
                "VERSION.CODENAME: " + android.os.Build.VERSION.CODENAME + "\n" +
                "TYPE: " + android.os.Build.TYPE + "\n" +
                "TAGS: " + android.os.Build.TAGS + "\n" +
                "FINGERPRINT: " + android.os.Build.FINGERPRINT + "\n" +
                "VERSION.BASE_OS: " + android.os.Build.VERSION.BASE_OS + "\n" +
                "VERSION.BASE_OS: " + android.os.Build.VERSION.BASE_OS + "\n" +
                "软件版本: "+getAppVersionName(MainActivity.this);

        Log.i(TAG, "onCreate: info=\n" + info);
        textView.setText(info);
    }

    private  String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.nanosic.home", 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

}

