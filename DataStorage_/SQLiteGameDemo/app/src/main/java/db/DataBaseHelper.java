package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "game.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_PLAYER = "create table if not exists player_table" +
            "(_id integer primary key autoincrement,player text,score integer,level integer)";
    private static final String DROP_TABLE_PLAYER = "drop table if exists player_table";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_PLAYER);
        db.execSQL(CREATE_TABLE_PLAYER);
    }
}
