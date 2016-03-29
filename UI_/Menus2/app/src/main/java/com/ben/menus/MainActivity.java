package com.ben.menus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//选项菜单
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
    //用于创建选项菜单的事件方法，在打开界面时候会被自动调用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//getMenuInflater获取一个菜单填充器
        //添加菜单项（组ID，当前选项ID，排序，名称）
//        menu.add(0,100,1,"游戏设置");
//        menu.add(0,200,2,"开始游戏");
//        menu.add(0,300,3,"退出游戏");
        return true;

    }
    //菜单选项的单击事件的响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id)
        {
//            case 100:
//                Toast.makeText(this,"设置游戏",Toast.LENGTH_SHORT).show();
//                break;
//            case 200:
//                Toast.makeText(this,"开始游戏",Toast.LENGTH_SHORT).show();
//                break;
//            case 300:
//                Toast.makeText(this,"退出游戏",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.game_set:
                Toast.makeText(this,"设置游戏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.game_start:
                Toast.makeText(this,"开始游戏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.game_exit:
                Toast.makeText(this,"退出游戏",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
