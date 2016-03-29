package com.ben.broadcastreceiver;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    private MyReceiver5 receiver5 = new MyReceiver5();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ben.action.MY_BROADCAST3");
        registerReceiver(receiver5,filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver5);
    }
}
