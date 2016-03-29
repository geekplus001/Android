package com.ben.media_playback;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void playClick(View view)
    {
        Intent intent = new Intent(MusicService.ACTION_PLAY);
        startService(intent);
    }
    public void pauseClick(View view)
    {
        Intent intent = new Intent(MusicService.ACTION_PAUSE);
        startService(intent);
    }
    public void exitClick(View view)
    {
        Intent intent = new Intent(MusicService.ACTION_EXIT);
        startService(intent);
    }

//    //退出去后发一个通知出来
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//        {
//            Notification.Builder builder = new Notification.Builder(this);
//            builder.setTicker("我的");
//            builder.setSmallIcon(R.mipmap.ic_launcher);
//            builder.setContentTitle("音乐神器");
//            builder.setContentInfo("正在播放");
//            PendingIntent pi = PendingIntent.getActivity(this,0,new Intent(this,Main3Activity.class),PendingIntent.FLAG_UPDATE_CURRENT);
//            builder.setContentIntent(pi);
//            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            Notification notification = builder.build();
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
