package com.ben.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

/*
监测电量变化
 */
public class MyReceiver8 extends BroadcastReceiver {
    public MyReceiver8() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int curr  = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        int total = intent.getIntExtra(BatteryManager.EXTRA_SCALE,1);
        int percent = curr * 100 / total;
        Toast.makeText(context,"当前电量为："+percent+"%",Toast.LENGTH_SHORT).show();
    }
}
