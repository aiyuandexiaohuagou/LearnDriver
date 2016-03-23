package com.nanosic.fragment1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mTableWeixin;
    private LinearLayout mTableFriend;

    private ContentFragment mWeixin;
    private FriendFragment mFriend;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);


        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mWeixin = new ContentFragment();
        transaction.replace(R.id.id_fragment_content, mWeixin);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mWeixin != null) {
            transaction.hide(mWeixin);
        }
        if (mFriend != null) {
            transaction.hide(mFriend);

        }

        switch (v.getId()) {
            case R.id.btn_1:
                Log.d(TAG, "onClick: R.id.btn_1");
                if (mWeixin == null) {
                    mWeixin = new ContentFragment();
                    transaction.add(R.id.id_fragment_content, mWeixin);
                } else {
                    transaction.show(mWeixin);
                }
                break;
            case R.id.btn_2:
                Log.d(TAG, "onClick: R.id.btn_2");
                if (mFriend == null) {
                    mFriend = new FriendFragment();
                    transaction.add(R.id.id_fragment_content, mFriend);
                }else {
                    transaction.show(mFriend);
                }
                //transaction.replace(R.id.id_fragment_content, mFriend);
        }

        transaction.commit();
    }
}
