package com.ben.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ICat cat;
    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startClick(View v)
    {
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("info", "给我一张票，我要回家过年");
        startService(intent);
    }

    public void stopClick(View v)
    {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    public void startIntentServiceClick(View v)
    {
        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("info","hehheehhehehehehe");
        startService(intent);
    }


    public void boundClick(View v)
    {
        Intent intent = new Intent(this,MyBoundService.class);
        //异步绑定，绑定成功后调用onServiceConnected方法
        bindService(intent,conn, Context.BIND_AUTO_CREATE);

    }


    public void unboundClick(View v)
    {
        if(mBound)
        {
            unbindService(conn);
            Toast.makeText(MainActivity.this,"解除绑定成功",Toast.LENGTH_SHORT).show();
        }

    }
    //绑定服务的连接回调接口
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定成功后回调的方法
            cat = ICat.Stub.asInterface(service);
            mBound = true;
            Toast.makeText(MainActivity.this,"绑定成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务异常时候调用
            mBound = false;
        }
    };


    public void callClick(View v)
    {
        if(cat == null)
        {
            return;
        }
        try {
            cat.setName("喵喵");

            Toast.makeText(this,cat.desc()+"\n"+cat.getPerson().toString(),Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    //***************************使用messenger
    Messenger mService;
    boolean flag = false;

    private ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            flag = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            flag = false;
            mService = null;
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        Intent service = new Intent(this,MessengerService.class);
        bindService(service, conn2, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(flag)
        {
            unbindService(conn2);
            flag = false;
        }

    }

    public void messengerClick(View v)
    {
        //获取(创建)一个消息对象
        Message message = Message.obtain();
        message.what = MessengerService.SAY_HELLO;
        message.obj = "fuck fuck";
        try {
            mService.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
