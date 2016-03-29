package com.ben.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/*
handler机制：
1、Message 消息对象，内部使用链表数据结构实现一个消息池，用于重复利用，避免大量创建消息对象
2、Handler 消息处理者，通过该对象把消息存入消息队列，并最后通过handlerManager方法处理消息
3、MessageQueue 消息队列，用于存储Message对象的数据结构，先进先出
4、Looper 消息队列处理者，用于循环检查消息队列，从消息队列中一个一个的取出消息对象，传入handlerMessage方法
 */
public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView  = (TextView) findViewById(R.id.textView);


    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 100:
                    textView.setText("下载完成");
                    break;
            }
        }
    };

    public void downloadClick(View v)
    {
        //使用线程模拟下载操作
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(true)
                        {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
//                        //下载完成后，更新UI状态
//                        textView.setText("下载完成");
                        handler.sendEmptyMessage(100);//发送一个空消息，标记为100

                        //上边的详写
                        Message msg = handler.obtainMessage();//获取一个消息对象
                        msg.what = 100;
                        msg.obj = "这里是要存储的信息";//任意类型
                        handler.sendMessage(msg);//发送消息
                        handler.sendEmptyMessageAtTime(100,System.currentTimeMillis()+3000);//在指定时间后发送消息
                        handler.sendEmptyMessageDelayed(100,200);//延迟多少时间后发送消息
                    }
                }
        ).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
