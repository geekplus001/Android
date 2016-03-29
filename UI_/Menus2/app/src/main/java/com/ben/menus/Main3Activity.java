package com.ben.menus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

//弹出菜单
public class Main3Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button = (Button) findViewById(R.id.select);
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

    public void typeSizeClick(View v)
    {
        PopupMenu popupMenu = new PopupMenu(this,v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_main3,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.s:
                Toast.makeText(this,"你选择了S号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.m:
                Toast.makeText(this,"你选择了M号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.l:
                Toast.makeText(this,"你选择了L号",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
