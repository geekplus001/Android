package com.ben.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_role = (Spinner)findViewById(R.id.spinner_role);

        String[] roles = {"管理员","VIP会员","普通会员","游客"};
        //方式一：
        //创建一个数组适配器（上下文，下拉列表里的布局文件，显示下拉选项的下拉组件的ID，数据）
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,roles);

        //方式二：（上下文，数据，布局）
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.city,android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //通过适配器进行数据的绑定
        spinner_role.setAdapter(adapter2);
    }
}
