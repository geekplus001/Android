package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import entity.GamePlayer;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public class DataBaseAdapter {
    private DataBaseHelper dataBaseHelper;

    public DataBaseAdapter(Context context)
    {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void add(GamePlayer gamePlayer)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GameMetaData.GamePlayer.PLAYER,gamePlayer.getPlayer());
        values.put(GameMetaData.GamePlayer.SCORE,gamePlayer.getScore());
        values.put(GameMetaData.GamePlayer.LEVEL,gamePlayer.getLevel());

        db.insert(GameMetaData.GamePlayer.TABLE_NAME,null,values);
        db.close();
    }

    public void delete(int id)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String whereClause = GameMetaData.GamePlayer._ID+"=?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(GameMetaData.GamePlayer.TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public void update(GamePlayer gamePlayer)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GameMetaData.GamePlayer.PLAYER,gamePlayer.getPlayer());
        values.put(GameMetaData.GamePlayer.SCORE,gamePlayer.getScore());
        values.put(GameMetaData.GamePlayer.LEVEL,gamePlayer.getLevel());
        String whereClause = GameMetaData.GamePlayer._ID+"=?";
        String[] whereArgs = {String.valueOf(gamePlayer.getId())};
        db.update(GameMetaData.GamePlayer.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    public GamePlayer findById(int id)
    {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = db.query(true, GameMetaData.GamePlayer.TABLE_NAME, null, GameMetaData.GamePlayer._ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        GamePlayer gamePlayer = null;
        if(c.moveToNext())
        {
            gamePlayer = new GamePlayer();
            gamePlayer.setId(c.getInt(c.getColumnIndexOrThrow(GameMetaData.GamePlayer._ID)));
            gamePlayer.setPlayer(c.getString(c.getColumnIndexOrThrow(GameMetaData.GamePlayer.PLAYER)));
            gamePlayer.setScore(c.getInt(c.getColumnIndexOrThrow(GameMetaData.GamePlayer.SCORE)));
            gamePlayer.setLevel(c.getInt(c.getColumnIndexOrThrow(GameMetaData.GamePlayer.LEVEL)));
        }
        c.close();
        db.close();
        return gamePlayer;
    }

    public ArrayList<GamePlayer> findAll()
    {
        String sql = "select _id,player,score,level from player_table order by score desc";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        ArrayList<GamePlayer> gamePlayers = new ArrayList<>();
        GamePlayer gamePlayer = null;
        while(c.moveToNext())
        {
            gamePlayer = new GamePlayer();
            gamePlayer.setId(c.getInt(c.getColumnIndexOrThrow(GameMetaData.GamePlayer._ID)));
            gamePlayer.setPlayer(c.getString(c.getColumnIndexOrThrow(GameMetaData.GamePlayer.PLAYER)));
            gamePlayer.setScore(c.getInt(c.getColumnIndexOrThrow(GameMetaData.GamePlayer.SCORE)));
            gamePlayer.setLevel(c.getInt(c.getColumnIndexOrThrow(GameMetaData.GamePlayer.LEVEL)));
            gamePlayers.add(gamePlayer);
        }
        c.close();
        db.close();
        return gamePlayers;
    }

    public int getCount()
    {
        int count = 0;
        String sql = "select count(_id) from player_table";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        count = c.getInt(0);
        c.close();
        db.close();
        return count;
    }
}
