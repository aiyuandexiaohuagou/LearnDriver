package com.nanosic.stringup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/4/1.
 */
public class DBController {
    DBOpenHelper dbOpenHelper;
    static private DBController dbController;

    static DBController getInstance(Context context) {
        if (dbController == null) {
            dbController = new DBController(context, null);
        }
        return dbController;
    }

    private DBController(Context context, String dbname) {
        this.dbOpenHelper = new DBOpenHelper(context, Define.DbName);
    }

    public String find(String s) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Define.tableName + " where " + Define.title + "=?", new String[]{s});
        String index = null;
        if(cursor.moveToFirst()){
            index = cursor.getString(cursor.getColumnIndex(Define.Pinyin));
        }

        cursor.close();
        return index;
    }

}
