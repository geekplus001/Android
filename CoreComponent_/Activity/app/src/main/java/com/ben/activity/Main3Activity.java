package com.ben.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
//        Bundle data  = intent.getBundleExtra("data");
//        String info = data.getString("info");

        String info = intent.getStringExtra("info");
        int age = intent.getIntExtra("age", 18);

//        Cat cat = (Cat) intent.getSerializableExtra("cat");

        Dog dog = intent.getParcelableExtra("dog");

//        textView.setText(info+"---"+age+"\n"+cat.toString()+"\n"+dog.toString());
        textView.setText(info+"---"+age+"\n"+dog.toString());
    }
}
