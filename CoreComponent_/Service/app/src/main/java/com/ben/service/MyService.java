package com.ben.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*
1、服务同时只会被创建一次，可以通过外部调用stopService或者stopSelf来终止服务
2、当执行一个已启动的服务时候，会直接调用onStartCommand方法来执行业务
3、默认情况下服务与主线程在同一进程中的同一线程中执行，如果服务执行一个比较耗时的操作，我们必须使用
子线程来完成工作，从而避免阻塞
4、使用started service启动的一个服务，在没有关闭之前会一直在后台运行
 */
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("my service create.");
    }

    /*
    此方法的返回值START_STICKY：被kill掉后会自己尝试重启（intent为null）
    not sticky：不尝试重启
    START_REDELIVER——INTENT：使用这个返回值时候，服务被异常kill掉系统会自动重启该服务并重传intent
     START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，不保证kill掉后一定能重启
     */
    //在该方法中实现服务的核心业务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println(intent.getStringExtra("info"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50;i++)
                {
                    System.out.println("onStartCommand-"+i+Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i==30)
                    {
                        MyService.this.stopSelf();
                        break;
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("my service destroy");
    }
}
