package com.ben.listview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView = (ListView) findViewById(R.id.listView3);


        //准备数据，每一个hashmap是一条记录
        HashMap<String,Object> title1 = new HashMap<>();
        title1.put("title","title1");
        title1.put("icon",android.R.drawable.alert_dark_frame);
        HashMap<String,Object> title2 = new HashMap<>();
        title2.put("title","title2");
        title2.put("icon",android.R.drawable.alert_light_frame);
        HashMap<String,Object> title3 = new HashMap<>();
        title3.put("title","title3");
        title3.put("icon",android.R.drawable.arrow_down_float);

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        list.add(title1);
        list.add(title2);
        list.add(title3);

        //把数据填充到Adapter中
//        SimpleAdapter<ArrayList<Map<String,String>>> sa = new SimpleAdapter();
        SimpleAdapter sa = new SimpleAdapter(this,list,R.layout.list_item_4,new String[]{"title","icon"},new int[]{R.id.textView_title,R.id.imageView_icon});
        listView.setAdapter(sa);

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

}
