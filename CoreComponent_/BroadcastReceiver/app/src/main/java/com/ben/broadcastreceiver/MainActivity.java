package com.ben.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyReceiver2 myReceiver2 = new MyReceiver2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //发送一个普通的广播
    public void sendNormalClick(View v)
    {
        Intent intent = new Intent("com.ben.action.MY_BROADCAST");
        intent.putExtra("info", "我们很有缘，终于对接了");
        this.sendBroadcast(intent);
    }

    //在这个方法中进行广播注册
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ben.action.MY_BROADCAST");
        registerReceiver(myReceiver2, filter);


        //立即获取当前电量
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        Intent batteryIntent = getApplicationContext().registerReceiver(null, intentFilter);
        int curr  = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        int total = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE,1);
        int percent = curr * 100 / total;
        Toast.makeText(this, "当前电量为：" + percent + "%", Toast.LENGTH_SHORT).show();
    }

    //在该方法中进行解除广播注册
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver2);
    }

    //发送一个有序广播
    public void sendOrderClick(View v)
    {
        Intent intent = new Intent("com.ben.action.MY_BROADCAST2");
        //参数：intent 接收权限
        this.sendOrderedBroadcast(intent, null);
    }

    //发送一个粘性广播
    public void sendStickyClick(View v)
    {
        Intent intent = new Intent("com.ben.action.MY_BROADCAST3");
        this.sendStickyBroadcast(intent);
    }

    public void openStickyActivityClick(View v)
    {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
