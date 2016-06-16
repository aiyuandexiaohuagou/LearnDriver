package com.godot.stringup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Objects;

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

    public String find(String s, boolean isfull) {
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        // 通过管理对象获取数据库
        Cursor cursor = null;
        SQLiteDatabase db = mg.getDatabase(Define.DbName);
        if (isfull) {
            cursor = db.rawQuery("select * from " + Define.tableName + " where " + Define.title + " = " + " ?", new String[]{s});
        } else {
            cursor = db.rawQuery("select * from " + Define.tableName + " where " + Define.title + " like " + " ?", new String[]{s + "%"});
            if (!cursor.moveToFirst()) {
                cursor = db.rawQuery("select " + Define.title + " from " + Define.tableName, null);
                if(cursor.moveToFirst()) {
                    String string = null;
                    while (cursor.moveToNext()) {
                        string = cursor.getString(cursor.getColumnIndex(Define.title));
                        if (Objects.equals(Pinyin.getPyOfWord(s), Pinyin.getPyOfWord(string.substring(0, 1)))) {
                            cursor.close();
                            return string;
                        }
                    }
                }
            }
        }


        String index = null;
        if(cursor.moveToFirst()){
            index = cursor.getString(cursor.getColumnIndex(Define.title));
        }

        cursor.close();
        return index;
    }

}
