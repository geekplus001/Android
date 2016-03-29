package com.ben.notifications;

import android.app.NotificationManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String msg = getIntent().getStringExtra("msg");
        TextView tv = (TextView) findViewById(R.id.msg);
        tv.setText(msg);
        //打开界面后取消指定ID的通知
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(MainActivity.NID_1);
    }
}
