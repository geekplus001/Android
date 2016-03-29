package com.ben.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void sendClick(View v)
    {
        Intent intent = new Intent(this,Main3Activity.class);
        String info = editText.getText().toString();
//        Bundle data = new Bundle();
//        data.putString("info",info);
//        intent.putExtra("data",data);
        //方式二
        intent.putExtra("info", info);
        intent.putExtra("age", 24);
        startActivity(intent);
    }

    public void sendObjClick(View v)
    {
        Cat cat = new Cat();
        cat.name = "小美";
        cat.age = 2;
        cat.type = "英短";

        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra("cat",cat);
        startActivity(intent);
    }


    public void sendObj2Click(View v)
    {
        Dog dog = new Dog();
        dog.name = "大美";
        dog.age = 3;
        dog.type = "萨摩耶";

        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra("dog",dog);
        startActivity(intent);
    }
}
