package com.ben.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main3Activity extends AppCompatActivity {


    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView = (ListView) findViewById(R.id.listView2);

        String [] arr = getResources().getStringArray(R.array.name);
//        //单选模式
//        ArrayAdapter <String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,arr);
//        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
        //多选模式
        ArrayAdapter <String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,arr);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(aa);
    }
}
