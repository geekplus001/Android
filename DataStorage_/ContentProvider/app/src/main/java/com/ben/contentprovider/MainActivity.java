package com.ben.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addClick(View view)
    {
        ContentResolver contentResolver = this.getContentResolver();
        //调用CP的添加方法
        //content://com.ben.contentprovider.hellocontentprovider/person
        //content://com.ben.contentprovider.hellocontentprovider/person/1
        Uri uri = Uri.parse("content://com.ben.contentprovider.hellocontentprovider/person");
        ContentValues values = new ContentValues();
        values.put(PersonMetaData.Person.NAME,"ben");
        values.put(PersonMetaData.Person.AGE, 18);
        contentResolver.insert(uri, values);
    }

    public void deleteClick(View view)
    {
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.ben.contentprovider.hellocontentprovider/person/1");
        contentResolver.delete(uri,null,null);
    }

    public void updateClick(View view)
    {
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.ben.contentprovider.hellocontentprovider/person/1");
        ContentValues values = new ContentValues();
        values.put(PersonMetaData.Person.NAME,"ben");
        values.put(PersonMetaData.Person.AGE, 20);
        contentResolver.update(uri,values,null,null);
    }
    public void queryClick(View view)
    {
       ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.ben.contentprovider.hellocontentprovider/person");
        Cursor c = contentResolver.query(uri,null,null,null,null);
        while (c.moveToNext())
        {
            System.out.println(c.getInt(c.getColumnIndexOrThrow(PersonMetaData.Person._ID)));
            System.out.println(c.getString(c.getColumnIndexOrThrow(PersonMetaData.Person.NAME)));
            System.out.println(c.getInt(c.getColumnIndexOrThrow(PersonMetaData.Person.AGE)));
        }
        c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
