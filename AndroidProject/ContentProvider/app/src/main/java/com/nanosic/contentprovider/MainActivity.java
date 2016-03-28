package com.nanosic.contentprovider;

import android.app.ListActivity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    private SimpleCursorAdapter adapter = null;
    private Cursor mCursor = null;
    private ContentResolver mContentResolver = null;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        initData();
        initAdapter();
    }

    private void initAdapter() {
        Log.d(TAG, "initAdapter: ");

        mCursor = mContentResolver.query(Profile.CONTEN_URI, new String[] {Profile.COLUMN_ID, Profile.COLUMN_NAME}, null, null, null);
        startManagingCursor(mCursor);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor,
                new String[]{Profile.COLUMN_ID, Profile.COLUMN_NAME},
                new int[] {android.R.id.text1, android.R.id.text2});
        setListAdapter(adapter);
    }

    private void initData() {
        Log.d(TAG, "initData: ");

        mContentResolver = getContentResolver();
        for (int i=0; i < 3; i++) {
            ContentValues values = new ContentValues();
            values.put(Profile.COLUMN_NAME, "Name" + i);
            mContentResolver.insert(Profile.CONTEN_URI, values);
        }
    }
}
