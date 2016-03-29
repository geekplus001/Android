package com.ben.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAlarmClick(View v)
    {
        //获取系统闹钟服务
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        long triggerTime = System.currentTimeMillis()+3000;
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent op = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
       //只会执行一次的闹钟
//        am.set(AlarmManager.RTC,triggerTime,op);
        //指定时间里重复执行闹钟
        am.setRepeating(AlarmManager.RTC, triggerTime, 2000, op);
    }

    public void startAlarmClick2(View v)
    {
        //获取系统闹钟服务
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        long triggerTime = System.currentTimeMillis()+3000;
        Intent intent = new Intent(this,Main2Activity.class);
        PendingIntent op = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //只会执行一次的闹钟
        am.set(AlarmManager.RTC,triggerTime,op);
        //指定时间里重复执行闹钟
//        am.setRepeating(AlarmManager.RTC, triggerTime, 2000, op);


    }
}
