package com.ben.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PhoneNumberListActivity extends AppCompatActivity {


    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_list);
        listView = (ListView) findViewById(R.id.listView);

        final String[] numbers = {"13592127437","18039223258","13460639215","13027711221","13007611042"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,numbers);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String number = numbers[position];
                Intent intent  = new Intent();
                intent.putExtra("number",number);
                setResult(RESULT_OK,intent);//设置返回结果
                finish();//结束当前界面
            }
        });

    }
}
