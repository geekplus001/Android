package com.ben.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
开机启动的广播接收器
 */
public class MyReceiver6 extends BroadcastReceiver {
    public MyReceiver6() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机启动",Toast.LENGTH_SHORT).show();
    }
}
