package com.ben.trackline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private static String DB_NAME = "track.db";//数据库名
    public static String TABLE_TRACK = "track";//表一
    public static String TABLE_TRACK_DETAIL = "track_detail";//表二

    //表一字段
    public static String ID = "_id";//外键
    //跟踪表
    public static String TRACK_NAME = "track_name";
    public static String CREATE_DATE = "create_date";
    public static String START_LOC = "start_loc";
    public static String END_LOC = "end_loc";

    //明细表（表二字段）
    public static String TID = "tid";//线路的ID
    public static String LAT = "lat";//纬度
    public static String LNg = "lng";//经度

    private static String CREATE_TABLE_TRACK = "create table track(_id integer primary key autoincrement,track_name text,create_date text,start_loc text,end_loc text)";
    private static String CREATE_TABLE_TRACK_DETAIL = "create table track_detail(_id integer primary key autoincrement,tid integer not null,lat real,lng real)";

    private static int VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRACK);
        db.execSQL(CREATE_TABLE_TRACK_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("drop table if exists track");
            db.execSQL("drop table if exists track_detail");
            db.execSQL(CREATE_TABLE_TRACK);
            db.execSQL(CREATE_TABLE_TRACK_DETAIL);
        }
    }
}
