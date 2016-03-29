package com.ben.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public class DataBaseAdapter {
    private DataBaseHelper dbHelper;

    public DataBaseAdapter(Context context)
    {
        dbHelper = new DataBaseHelper(context);
    }
//事务处理
    public void transaction()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();//开始事务
        try {
            db.execSQL("insert into dog(name,age) values('duang',4)");
            db.execSQL("insert into dog(name,age) values('guang',5)");
            db.setTransactionSuccessful();//设置事物成功标记（true）
        }finally {
            db.endTransaction();//结束事务，如果事务标记为true，那么提交事务否则回滚
        }
        db.close();
    }

    public void rawAdd(Dog dog)
    {
        String sql = "insert into dog(name,age) values(?,?)";
        Object [] args = {dog.getName(),dog.getAge()};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql,args);
        db.close();
    }

    public void add(Dog dog)
    {
        //获取操作数据库的工具类
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PetMetaData.DogTable.NAME,dog.getName());
        values.put(PetMetaData.DogTable.AGE,dog.getAge());

        //参数（表名，可以为空的列名，ContentValues）
        //合法：insert into dog(name,age) values('xx'.2)
        //不合法：insert into dog(name) values(null)
        db.insert(PetMetaData.DogTable.TABLE_NAME, PetMetaData.DogTable.NAME, values);
        db.close();
    }
    public void rawDelete(int id)
    {
        String sql = "delete from dog where _id=?";
        Object [] args = {id};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, args);
        db.close();
    }
    public void delete(int id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = PetMetaData.DogTable._ID+"=?";
        String[] whereArgs = {String.valueOf(id)};

        //参数（表名，条件，条件的值）
        db.delete(PetMetaData.DogTable.TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
    public void rawUpdate(Dog dog)
    {
        String sql = "update dog set name=?,age=? where _id=?";
        Object [] args = {dog.getName(),dog.getAge(),dog.getId()};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, args);
        db.close();
    }
    public void update(Dog dog)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PetMetaData.DogTable.NAME,dog.getName());
        values.put(PetMetaData.DogTable.AGE,dog.getAge());
        String whereClause = PetMetaData.DogTable._ID+"=?";
        String[] whereArgs = {String.valueOf(dog.getId())};

        //参数（表名，ContentValues，条件，条件的值）
        db.update(PetMetaData.DogTable.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }
    public Dog rawFindById(int id)
    {
        String sql = "select _id,name,age from dog where _id=?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql,new String[]{String.valueOf(id)});
        Dog dog = null;
        if(c.moveToNext())
        {
            dog = new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
        }
        c.close();
        db.close();
        return dog;
    }
    public Dog findDogById(int id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        //参数（是否去重，表名，查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页条件）
        Cursor c = db.query(true, PetMetaData.DogTable.TABLE_NAME, columns, PetMetaData.DogTable._ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        Dog dog = null;
        if(c.moveToNext())
        {
            dog = new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
        }
        c.close();
        db.close();
        return dog;
    }
    public ArrayList<Dog> rawFindAll()
    {
        String sql = "select _id,name,age from dog";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql,null);
        ArrayList<Dog> dogs = new ArrayList<>();
        Dog dog = null;
        while(c.moveToNext())
        {
            dog = new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
            dogs.add(dog);
        }
        c.close();
        db.close();
        return dogs;
    }
    public ArrayList<Dog> findAllDog()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        //参数（是否去重，表名，查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页条件）
        Cursor c = db.query(true, PetMetaData.DogTable.TABLE_NAME, columns, null, null, null, null, null, null);

        ArrayList<Dog> dogs = new ArrayList<>();
        Dog dog = null;
        while(c.moveToNext())
        {
            dog = new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
            dogs.add(dog);
        }
        c.close();
        db.close();
        return dogs;
    }

}
