package com.ben.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "pet.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_DOG = "CREATE TABLE dog(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT,age INTEGER)";
    private static final String DROP_TABLE_DOG = "DROP TABLE IF EXISTS dog";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //如果数据库不存在那么会调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDatabase 用于操作数据库的工具类
        db.execSQL(CREATE_TABLE_DOG);
    }

    //升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_DOG);
        db.execSQL(CREATE_TABLE_DOG);
    }
}
