package com.ben.service;

import android.app.IntentService;
import android.content.Intent;

/*
IntentService：
1、内部有一个工作线程来完成耗时操作，只需实现onHandleIntent方法即可
2、完成工作后自动停止服务
3、如果同时执行多个任务时候，会以工作队列的方式，一次进行
4、通过使用该类来完成本APP中的耗时工作
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println(intent.getStringExtra("info"));
        for(int i=0;i<50;i++)
        {
            System.out.println("onHandleIntent--"+i+"--"+Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
