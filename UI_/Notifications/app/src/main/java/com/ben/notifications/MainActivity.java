package com.ben.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

     static final int NID_1 = 0x1;
    private static final int NID_2 = 0x2;
    private static final int NID_3 = 0x3;
    private static final int NID_4 = 0x4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //发送一个普通的通知
    public void sendNotification1(View v)
    {
        //API11 之前创建通知的方式
//        Notification n = new Notification();
        //11 之后
//        Notification.Builder builder = new Notification.Builder(this);
        //v4支持包
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this);
        //设置相关属性
        builder1.setSmallIcon(R.mipmap.ic_launcher);
        builder1.setContentTitle("您有新的消息");
        builder1.setContentText("万事如意");
//        builder1.setOngoing(true);//设置常驻通知
//        builder1.setAutoCancel(true);
        builder1.setDefaults(Notification.DEFAULT_ALL);
        builder1.setTicker("新消息");
        builder1.setNumber(10);


        //定义一个意图，当点击通知时要打开另一个界面（Activity）
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("msg","万事如意");
        /*
        参数：上下文，请求编码（无用），意图，创建PendingIntent的方式
        PendingIntent.Flag_cancel_current:取消当前PI，创建新的
        PendingIntent.Flag_no_create:有就用没有不创建
        PendingIntent.Flag_one_shot:只使用一次
        PendingIntent.Flag_update_current:有就更新Intent没有就创建
         */
        PendingIntent pi = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //通知的事件
        builder1.setContentIntent(pi);



        //创建一个通知对象
        Notification n = builder1.build();
        //获取系统的通知管理器，然后发送通知
        NotificationManager nm  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_1,n);
    }



    public void sendNotification2(View v)
    {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("content_title");
        builder.setContentText("content_text");
        //设置大视图样式
        Notification.InboxStyle style = new Notification.InboxStyle();
        style.setBigContentTitle("big_title吟诗作对");
        style.addLine("长亭外");
        style.addLine("古道边");
        style.addLine("一行白鹭上青天");
        builder.setStyle(style);

        style.setSummaryText("作者：逗比");

        Notification n = builder.build();
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_2,n);
    }

    //显示进度条的通知
    public void sendNotification3(View v) {
        final Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("更新中...");
        builder.setContentText("装逼模式开启中");

        builder.setProgress(100, 10, false);

        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_3,builder.build());


        //模拟一个更新进程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int progress=0;progress<=100;progress+=5)
                {
                    builder.setProgress(100,progress,false);
                    nm.notify(NID_3, builder.build());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setProgress(0,0,false);
                builder.setContentText("装逼模式已开启");
                nm.notify(NID_3,builder.build());
            }
        }).start();
    }


    //发送自定义视图
    public void sendNotification4(View v)
    {
        Notification.Builder builder = new Notification.Builder(this);



        builder.setSmallIcon(R.mipmap.ic_launcher);



        //创建一个远程视图
        RemoteViews views = new RemoteViews(getPackageName(),R.layout.custom_layout);

        views.setTextViewText(R.id.textView_song_name,"第一滴泪");
//        views.setImageViewResource();
        views.setTextViewText(R.id.button2_play,"暂停");
//        views.setOnClickPendingIntent(); //设置按钮的单击事件

        builder.setContent(views);
        builder.setTicker("快播");
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_4,builder.build());
    }
}
