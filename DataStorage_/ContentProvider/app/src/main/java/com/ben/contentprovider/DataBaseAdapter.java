package com.ben.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class DataBaseAdapter {

    private DataBaseHelper dataBaseHelper;
    public DataBaseAdapter(Context context)
    {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void save(Person person)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonMetaData.Person.NAME,person.getName());
        values.put(PersonMetaData.Person.AGE,person.getAge());

        db.insert(PersonMetaData.Person.TABLE_NAME,null,values);
        db.close();
    }

    public void delete(int id)
    {
        SQLiteDatabase db  = dataBaseHelper.getWritableDatabase();
        db.delete(PersonMetaData.Person.TABLE_NAME, PersonMetaData.Person._ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(Person person)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(PersonMetaData.Person.NAME,person.getName());
        values.put(PersonMetaData.Person.AGE,person.getAge());
        String whereClause = PersonMetaData.Person._ID+"=?";
        String [] whereArgs = {String.valueOf(person.getId())};

        db.update(PersonMetaData.Person.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    public Person findById(int id)
    {
        SQLiteDatabase db  = dataBaseHelper.getReadableDatabase();
        Cursor c = db.query(PersonMetaData.Person.TABLE_NAME, null, PersonMetaData.Person._ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
        Person person = null;
        if(c.moveToNext())
        {
            person = new Person();
            person.setId(c.getInt(c.getColumnIndexOrThrow(PersonMetaData.Person._ID)));
            person.setName(c.getString(c.getColumnIndexOrThrow(PersonMetaData.Person.NAME)));
            person.setAge(c.getInt(c.getColumnIndexOrThrow(PersonMetaData.Person.AGE)));
        }
        c.close();
        db.close();
        return person;
    }

    public ArrayList<Person> findAll()
    {
        SQLiteDatabase db  = dataBaseHelper.getReadableDatabase();
        Cursor c = db.query(PersonMetaData.Person.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Person> persons = new ArrayList<>();
        Person person = null;
        while(c.moveToNext())
        {
            person = new Person();
            person.setId(c.getInt(c.getColumnIndexOrThrow(PersonMetaData.Person._ID)));
            person.setName(c.getString(c.getColumnIndexOrThrow(PersonMetaData.Person.NAME)));
            person.setAge(c.getInt(c.getColumnIndexOrThrow(PersonMetaData.Person.AGE)));
            persons.add(person);
        }
        c.close();
        db.close();
        return persons;
    }

    //静态内部类防止内存溢出
    public static class DataBaseHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = "cp.db";
        private static final int VERSION = 1;
        private static final String CREATE_TABLE = "CREATE TABLE person(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,age INT )";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS person";
        public DataBaseHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        }
    }
}
