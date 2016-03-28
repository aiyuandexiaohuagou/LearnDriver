package com.nanosic.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.sql.SQLData;
import java.sql.SQLException;
import java.util.Map;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * Created by Administrator on 2016/3/24.
 */
public class MyProvider extends ContentProvider {
    private static final String TAG = "MyProvider";

    DBHelper mDbHelper = null;
    SQLiteDatabase db = null;

    private static final UriMatcher mMatcher;
    static {
        Log.d(TAG, "static initializer: ");
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(Profile.AUTHORITY, Profile.TABLE_NAME, Profile.ITEM);
        mMatcher.addURI(Profile.AUTHORITY, Profile.TABLE_NAME + "/#", Profile.ITEM_ID);
    }


    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        mDbHelper = new DBHelper(getContext());
        db = mDbHelper.getReadableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query: " + "uri=" + uri + "\n"
        + "projection=" + projection+ "\n"
        + "selection=" + selection + "\n"
        + "selectionArgs=" + selectionArgs + "\n"
        + "sortOrder=" + sortOrder + "\n");

        for (int i=0; i < projection.length; i++) {
            Log.d(TAG, String.format("projection[%d]=%s", i, projection[i]));
        }

        Cursor c = null;
        switch (mMatcher.match(uri)) {
            case Profile.ITEM:
                c = db.query(Profile.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Profile.ITEM_ID:
                c = db.query(Profile.TABLE_NAME, projection, Profile.COLUMN_ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType: ");
        switch (mMatcher.match(uri)) {
            case Profile.ITEM:
                return Profile.CONTENT_TYPE;
            case Profile.ITEM_ID:
                return Profile.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert: " + "\n"
        + "uri=" + uri + "\n"
        + "values=" + values);

        long rowId;
        if (mMatcher.match(uri) != Profile.ITEM) {
            throw new IllegalArgumentException("Unkown URI" + uri);
        }

        rowId = db.insert(Profile.TABLE_NAME, null, values);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(Profile.CONTEN_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete: ");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update: ");
        return 0;
    }
}
