package com.ben.asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/*
   1、AsyncTask的实例必须在UI Thread中创建
   2、execute方法必须在UI Thread中调用
   3、不要手动调用onPreExecute。。。几个方法
   4、该Task只能被执行一次，否则多次调用时候会出现异常
   5、AsyncTask不能完全取代线程，在逻辑较为复杂或者需要在后台反复执行的逻辑需要线程实现
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void AsyncTaskClick(View v)
    {
        new MyAsyncTask(this).execute();
    }
    /*
    通过AsyncTask实现一个异步任务
     */
    private static class MyAsyncTask extends AsyncTask{
        private MainActivity activity;
        public MyAsyncTask(MainActivity activity)
        {
            this.activity = activity;
        }


        //执行任务前触发，做些初始化工作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute");
            activity.textView.setText("开始执行任务//////////");
        }
        //执行后台任务的方法，类似于线程，所以不能在该方法中访问UI组件
        @Override
        protected Object doInBackground(Object[] params) {

            for(int i=0;i<10;i++)
            {
                System.out.println(i);
                publishProgress(i);//更新进度（调用onProcessUpdate方法）
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "success";
        }

        //更新进度值
        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            activity.textView.setText("当前值为："+values[0]);
        }

        //当doBackGround方法返回后被调用
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            activity.textView.setText(o.toString());
        }
    }


    public void downloadClick(View v)
    {
        new DownloadAsyncTask(this).execute("http://pic35.nipic.com/20131114/13610382_233444193301_2.jpg");
    }

    private static class DownloadAsyncTask extends AsyncTask{

        private MainActivity activity;
        public DownloadAsyncTask(MainActivity activity)
        {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.progressBar.setProgress(0);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String s = (String) params[0];
            try {
                URL url = new URL(s);
                //打开链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                int size = conn.getContentLength();
                //0标记表示需要更新最大进度值    1表示更新当前下载的进度值
                publishProgress(0,size);
                byte[] bytes = new byte[20];
                int len = -1;
                InputStream in = conn.getInputStream();
                FileOutputStream out = new FileOutputStream(
                        "/sdcard/"+System.currentTimeMillis()+".jpg");
                while((len=in.read(bytes))!=-1)
                {
                    out.write(bytes,0,len);
                    publishProgress(1, len);//更新进度
                    out.flush();
                }
                out.close();
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 200;
        }

        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);
            switch (values[0]){
                case 0:
                    activity.progressBar.setMax(values[1]);
                    break;
                case 1:
                    activity.progressBar.incrementProgressBy(values[1]);
                    break;
            }
        }

        protected void onPostExecute(Integer o) {
            super.onPostExecute(o);
            if(o==200)
            {
                activity.textView.setText("下载完成");
            }
        }
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
