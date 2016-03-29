package com.ben.buttonimagebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/*
实现onClick接口
1、实现View.OnClickListener方法
2、注册事件
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button b1,b2,b3;
    private Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        //注册事件
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        //使用内部类方式
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"使用内部类的方式",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void click1(View v)
    {
        Toast.makeText(this,"onCLick-",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
                Toast.makeText(this,"button-1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this,"button-2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this,"button-3",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
