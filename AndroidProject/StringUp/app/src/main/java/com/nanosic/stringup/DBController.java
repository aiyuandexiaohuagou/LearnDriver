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
        //this.dbOpenHelper = new DBOpenHelper(context, Define.DbName);
        // 初始化，只需要调用一次
        AssetsDatabaseManager.initManager(context);
    }

    public String findId(int id) {
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        // 通过管理对象获取数据库

        SQLiteDatabase db = mg.getDatabase(Define.DbName);
        //db.rawQuery("select * from person where name like ? and age=?", new String[]{"%林计钦%", "4"});
        Cursor cursor = db.rawQuery("select * from " + Define.tableName + " where " + Define.id + " =?", new String[]{Integer.toString(id)});
        String index = null;
        if(cursor.moveToFirst()){
            index = cursor.getString(cursor.getColumnIndex(Define.title));
        }

        cursor.close();
        return index;
    }

    public String find(String s) {
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        // 通过管理对象获取数据库

        SQLiteDatabase db = mg.getDatabase(Define.DbName);
        //db.rawQuery("select * from person where name like ? and age=?", new String[]{"%林计钦%", "4"});
        Cursor cursor = db.rawQuery("select * from " + Define.tableName + " where " + Define.title + " like " + " ?", new String[]{s + "%"});
        String index = null;
        if(cursor.moveToFirst()){
            index = cursor.getString(cursor.getColumnIndex(Define.title));
        }

        cursor.close();
        return index;
    }

}
