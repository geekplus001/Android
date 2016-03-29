package com.ben.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

//线程安全，不支持并发（一般用不上并发）

public class MessengerService extends Service {

    public static final int SAY_HELLO = 0x1;

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  messenger.getBinder();
    }

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SAY_HELLO:
                    String info = (String) msg.obj;
                    Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private Messenger messenger = new Messenger(handler);
}
