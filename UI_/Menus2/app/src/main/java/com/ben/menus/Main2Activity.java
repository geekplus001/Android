package com.ben.menus;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//上下文菜单
public class Main2Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.context_bg);
        //注册上下文菜单到组件
        registerForContextMenu(textView);
    }
    //创建上下文菜单的事件方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main2,menu);
    }
    //上下文菜单的单击事件
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.red:
                textView.setBackgroundColor(Color.RED);
                 break;
            case R.id.green:
                textView.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue:
                textView.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
