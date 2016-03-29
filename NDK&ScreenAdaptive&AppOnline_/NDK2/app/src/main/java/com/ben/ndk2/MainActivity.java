package com.ben.ndk2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText_num1,editText_num2;
    private TextView textView_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_num1 = (EditText) findViewById(R.id.editText);
        editText_num2 = (EditText) findViewById(R.id.editText2);
        textView_result = (TextView) findViewById(R.id.textView);
    }

    //声明一个本地方法，该方法由C 、C++实现
    public native int add(int num1,int num2);

    public void addCLick(View view)
    {
        String num1 = editText_num1.getText().toString();
        String num2 = editText_num2.getText().toString();
        //调用本地方法
        int result = add(Integer.parseInt(num1),Integer.parseInt(num2));
    }
}
