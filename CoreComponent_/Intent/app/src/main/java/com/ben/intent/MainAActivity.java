package com.ben.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
Activity的四种启动模式：android:launchMode
standard：默认的模式，每次启动会新创建一个Activity
singleTop:在当前任务栈中，判断栈顶是否为当前的Activity，
如果是，就直接使用，不是再创建新的Activity放入栈顶
singleTask:在当前任务栈中是否存在Activity，
如果不存在，创建一个新的Activity，
如果存在，会把该Activity之上的所有Activity清除出栈，显示当前Activity
singleInstance；新创建一个任务栈，放入新创建的Activity，
该任务栈只允许存在一个Activity实例；如果已存在那么切换到该任务栈
 */
public class MainAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_a);
    }
    public void startA(View v)
    {
        Intent intent = new Intent(this,MainAActivity.class);
        startActivity(intent);
    }
    public void startB(View v)
    {
        Intent intent = new Intent(this,MainBActivity.class);
        startActivity(intent);
    }
}
