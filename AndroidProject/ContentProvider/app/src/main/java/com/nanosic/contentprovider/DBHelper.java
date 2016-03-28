package com.nanosic.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    /*database name*/
    private static final String DATABASE_NAME = "test.db";

    /*database version*/
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "DBHelper: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Profile.TABLE_NAME +
                "(" + Profile.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Profile.COLUMN_NAME + " VARCHAR NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
        db.execSQL("DROP TABLE IF EXISTS " + Profile.TABLE_NAME);
        onCreate(db);
    }
}
