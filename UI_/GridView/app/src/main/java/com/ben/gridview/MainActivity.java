package com.ben.gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/*
GridView组件：
1、自定义适配器
创建一个类，继承BaseAdapter类
实现四个方法（）
 */

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter(this));
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

    //自定义适配器(形态内部类相当于一个外部类，效率高)
    static class MyAdapter extends BaseAdapter{

        private int[] images = {R.mipmap.a,R.mipmap.aaa,R.mipmap.b,R.mipmap.c,
        R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,R.mipmap.i,
                R.mipmap.j,R.mipmap.k,R.mipmap.l,
        R.mipmap.m,R.mipmap.n};
        private Context context;
        public  MyAdapter(Context context)
        {
            this.context = context;
        }
        //获取要显示的选项总数
        @Override
        public int getCount() {
            return images.length;
        }
        //获取每一个选项
        @Override
        public Object getItem(int position) {
            return images[position];
        }
        //选项ID
        @Override
        public long getItemId(int position) {
            return position;
        }
        //获取一个选项视图，会被多次调用
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv  = new ImageView(context);
            iv.setImageResource(images[position]);
            return iv;
        }
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
