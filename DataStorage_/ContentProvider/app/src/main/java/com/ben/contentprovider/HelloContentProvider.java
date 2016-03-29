package com.ben.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class HelloContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.ben.contentprovider.hellocontentprovider";
    //创建一个Uri匹配器
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int MULTIPLE_CODE = 1;//返回多个记录的匹配码
    private static final int SINGLE_CODE = 2;//返回单个记录的匹配码

    //text/plain image/jpg
    private static final String SINGLE_TYPE = "vnd.android.cursor.item/person";
    private static final String MULTIPLE_TYPE = "vnd.android.cursor.dir/person";

    static
    {
        //content://com.ben.contentprovider.hellocontentprovider/person
        uriMatcher.addURI(AUTHORITY,"person",MULTIPLE_CODE);
        //content://com.ben.contentprovider.hellocontentprovider/person/
        //#代表数字  *代表字符
        uriMatcher.addURI(AUTHORITY,"person/#",SINGLE_CODE);
    }

    private DataBaseAdapter.DataBaseHelper dataBaseHelper;

    @Override
    public boolean onCreate() {
	//
	//
	//
        //Activity Service:this  BroadCast:传参   ContentProvider:getContext
        dataBaseHelper = new DataBaseAdapter.DataBaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri))
        {
            case SINGLE_CODE:
                SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
                long id = ContentUris.parseId(uri);
                selection = PersonMetaData.Person._ID+"=?";
                selectionArgs = new String[]{String.valueOf(id)};
                return db.query(PersonMetaData.Person.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case MULTIPLE_CODE:
                db = dataBaseHelper.getReadableDatabase();
                return db.query(PersonMetaData.Person.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri))
        {
            case SINGLE_CODE:
                return SINGLE_TYPE;
            case MULTIPLE_CODE:
                return  MULTIPLE_TYPE;
        }
        return null;
    }

    //content://com.ben.contentprovider.hellocontentprovider/person
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri))
        {
            case MULTIPLE_CODE:
                SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

                long id = db.insert(PersonMetaData.Person.TABLE_NAME,null,values);
                uri = ContentUris.withAppendedId(uri, id);
                db.close();
                break;
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri))
        {
            //content://com.ben.contentprovider.hellocontentprovider/person/1
            case SINGLE_CODE:
                SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                selection = PersonMetaData.Person._ID+"=?";
                selectionArgs = new String[]{String.valueOf(id)};
                int row = db.delete(PersonMetaData.Person.TABLE_NAME, selection, selectionArgs);
                db.close();
                return row;
            case MULTIPLE_CODE:
                 db = dataBaseHelper.getWritableDatabase();
                row = db.delete(PersonMetaData.Person.TABLE_NAME,selection,selectionArgs);
                db.close();
                return row;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri))
        {
            //content://com.ben.contentprovider.hellocontentprovider/person/1
            case SINGLE_CODE:
                SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                selection = PersonMetaData.Person._ID+"=?";
                selectionArgs = new String[]{String.valueOf(id)};
                int row = db.update(PersonMetaData.Person.TABLE_NAME,values, selection, selectionArgs);
                db.close();
                return row;
            case MULTIPLE_CODE:
                db = dataBaseHelper.getWritableDatabase();
                row = db.update(PersonMetaData.Person.TABLE_NAME,values,selection,selectionArgs);
                db.close();
                return row;
        }
        return 0;
    }
}
