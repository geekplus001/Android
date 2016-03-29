package com.ben.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver4 extends BroadcastReceiver {
    public MyReceiver4() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "有序广播-2", Toast.LENGTH_SHORT).show();
        Bundle data = new Bundle();
        data.putString("info","广播-2");
        setResultExtras(data);
        //中断有序广播
        this.abortBroadcast();
    }
}
