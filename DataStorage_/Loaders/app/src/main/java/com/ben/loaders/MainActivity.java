package com.ben.loaders;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private DataBaseAdapter dataBaseAdapter;

    SimpleCursorAdapter dataAdapter;

    CursorLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseAdapter = new DataBaseAdapter(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        dataAdapter = new SimpleCursorAdapter(this,
                R.layout.list_item,dataBaseAdapter.list(),
                new String[]{PersonMetaData.Person._ID,PersonMetaData.Person.NAME,PersonMetaData.Person.AGE},
                new int[]{R.id.textView_id,R.id.textView2_name,R.id.textView3_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(dataAdapter);

        //初始化一个loader（id，bundle参数，回调接口）
        getLoaderManager().initLoader(0,null,this);

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
        dataBaseAdapter.save(new Person("ben",18));
        //重启加载器
//        getLoaderManager().restartLoader(0,null,this);
//        loader.commitContentChanged();   //API18可用
        loader.onContentChanged();//内容发生了变化，通知加载器
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse("content://com.ben.personcontentprovider/person");
        //创建一个游标加载器（上下文，CP的URI，要查询的列数组，查询条件，查询条件的值，排序条件）
         loader = new CursorLoader(this,uri,null,null,null,null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        dataAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dataAdapter.swapCursor(null);
    }
}
