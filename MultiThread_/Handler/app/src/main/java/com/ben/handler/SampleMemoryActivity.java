package com.ben.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/*
handler的内存泄露问题
1、定义一个内部类时候，会默认拥有外部类对象的引用，
所以建议使用内部类时候，最好定义为一个静态内部类
2、引用的强弱：
强引用（new的 不自动回收）-》软引用（引用另一个对象的，内存不足才会回收）-》弱引用（对象不存在就拿不到）-》虚引用
 */

public class SampleMemoryActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_memory);
        textView = (TextView) findViewById(R.id.textView);

        //使用Handler延迟执行一个Runnable十分钟
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("内存泄露");
            }
        },1000*60*10);
    }

//    private Handler handler = new Handler()
//    {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };

    private MyHandler handler = new MyHandler(this);

    private static class MyHandler extends Handler{
        WeakReference<SampleMemoryActivity> weakReference;
        public MyHandler(SampleMemoryActivity activity)
        {
            weakReference = new WeakReference<SampleMemoryActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SampleMemoryActivity activity = weakReference.get();
            if(activity!=null)
            {
//                textView.setText("");
            }
        }
    }
}
