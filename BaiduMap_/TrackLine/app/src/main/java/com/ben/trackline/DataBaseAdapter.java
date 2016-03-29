package com.ben.trackline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class DataBaseAdapter {
    private DataBaseHelper dataBaseHelper;

    public DataBaseAdapter(Context context)
    {
        dataBaseHelper = new DataBaseHelper(context);
    }

    //添加线路跟踪
    public int addTrack(Track track)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.TRACK_NAME,track.getTrack_name());
        values.put(DataBaseHelper.CREATE_DATE,track.getCreate_date());
        values.put(DataBaseHelper.START_LOC,track.getStart_loc());
        values.put(DataBaseHelper.END_LOC, track.getEnd_loc());
        long id = db.insertOrThrow(DataBaseHelper.TABLE_TRACK,null,values);
        db.close();
        return (int) id;
    }

    //更新终点地址
    public void updateEndLoc(String endLoc,int id)
    {
        String sql = "update track set end_loc=? where _id=?";
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.execSQL(sql,new Object[]{endLoc,id});
        db.close();
    }

    //添加线路跟踪明细
    public void addTrackDetail(int tid,double lat,double lng)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "insert into track_detail(tid,lat,lng) values(?,?,?)";
        db.execSQL(sql,new Object[]{tid,lat,lng});
        db.close();
    }
    //根据ID查询线路跟踪
    public ArrayList<TrackDetail> getTrackDetails(int id)
    {
        String sql = "select _id,lat,lng from track_detail where tid=? order by _id desc";
        ArrayList<TrackDetail> list = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql,new String[]{String.valueOf(id)});
        if(c!=null)
        {
            TrackDetail detail = null;
            while(c.moveToNext())
            {
                detail = new TrackDetail(c.getInt(0),c.getDouble(1),c.getDouble(2));
                list.add(detail);
            }
            c.close();
        }
        db.close();
        return list;
    }

    //查询所有路线
    public ArrayList<Track> getTracks()
    {
        ArrayList<Track> tracks = new ArrayList<>();
        String sql = "select _id,track_name,create_date,start_loc,end_loc from track";
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);
        Track t = null;
        if(c!=null)
        {
            while(c.moveToNext())
            {
                t = new Track(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                tracks.add(t);
            }
            c.close();
        }
        db.close();
        return tracks;
    }
    //根据ID删除线路跟踪
    public void delTrack(int id)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql1 = "delete from track where _id=?";
        String sql2 = "delete from track_detail where tid=?";
        try{
            db.beginTransaction();
            db.execSQL(sql2,new Object[]{id});
            db.execSQL(sql1,new Object[]{id});
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            if(db!=null)
            {
                db.close();
            }
        }
    }

}
