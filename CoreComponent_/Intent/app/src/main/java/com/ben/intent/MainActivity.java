package com.ben.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*
    直接查找法：通过组件名称
     */
    public void componentClick(View v)
    {
//        Intent intent = new Intent();
//        ComponentName componentName  = new ComponentName(this,Main2Activity.class);
//        intent.setComponent(componentName);
        //方法二（常用）
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    /*
    间接法（通过action属性category属性）
    在Activity清单文件配置中，必须有默认的类别：android.intent.category.DEFAULT
     */
    public void actionClick(View v)
    {
        Intent intent = new Intent("com.ben.intent.MY_Action");
//        intent.setAction("com.ben.intent.MY_Action");
        intent.addCategory("com.ben.intent.MY_CATEGORY");
        startActivity(intent);
    }

    /*
    间接法（data属性，一般与Action配合使用）
    type：表示数据的类型
     */
    public void dataClick(View v)
    {
        Intent intent  = new Intent();
//        intent.setAction("com.ben.intent.MY_Action");
        intent.setAction(Intent.ACTION_VIEW);
        Uri data = Uri.parse("http://www.baidu.com");
//        intent.setData(data);//不能分别单独设置data和type，会分别使对方为空
        intent.setDataAndType(data,"text/html");//匹配时候必须两者同时匹配
        startActivity(intent);
    }



    public void flagClick(View v)
    {
        Intent intent = new Intent(this,Main5Activity.class);
        //设置Activity的启动模式
        //FLAG_ACTIVITY_NEW_TASK在一个新的任务栈中启动Activity，若果有就在本任务中启动
        //FLAG_ACTIVITY_CLEAR_TASK相当于singleTask
        //FLAG_ACTIVITY_CLEAR_TOP相当于singleTop
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
