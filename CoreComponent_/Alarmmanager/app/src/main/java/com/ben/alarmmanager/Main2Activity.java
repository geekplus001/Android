package com.ben.alarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

public class Main2Activity extends Activity {

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //唤醒屏幕
        Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_main2);
        alarmDialog();
        mp = new MediaPlayer();
        try {
            mp.setDataSource(this, Uri.parse("/sdcard/music/**.mp3"));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setLooping(true);

        mp.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp!=null)
        {
            if(mp.isPlaying())
            {
                mp.stop();
            }
            mp.release();
        }
    }

    public void alarmDialog()
    {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setMessage("逗比，起床了！");
        builder.setPositiveButton("再响", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alarm();
                finish();
            }
        });

        builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    private void alarm()
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
