package com.ben.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.widget.Toast;

public class MyReceiver3 extends BroadcastReceiver {
    public MyReceiver3() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data =  getResultExtras(false);
        String info = data.getString("info");
        Toast.makeText(context,"有序广播-1-----"+info,Toast.LENGTH_SHORT).show();

    }
}
