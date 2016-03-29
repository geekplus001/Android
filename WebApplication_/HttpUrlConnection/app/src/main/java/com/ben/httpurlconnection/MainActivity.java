package com.ben.httpurlconnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
/*
访问网络的操作必须在工作线程中完成
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final int LOAD_SUCCESS = 0x1;
    private final MyHandler handler = new MyHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView111);

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


    private static class MyHandler extends Handler{

        private final WeakReference<MainActivity> weakReference;

        private MyHandler(MainActivity mainActivity) {
            weakReference = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = weakReference.get();
            if(mainActivity!=null)
            {
                switch (msg.what){
                    case LOAD_SUCCESS:
                        mainActivity.imageView.setImageBitmap((Bitmap) msg.obj);
                        System.out.println("45465644676876876874");
                        break;
                }
            }
        }
    }


    public void showImageClick(View view)
    {
       new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           URL url = new URL("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%9B%BE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=3698838387,3931421345&os=3996995534,2022352959&simid=4025432468,541071455&pn=54&rn=1&di=115439354800&ln=1000&fr=ala&fmq=1451203950084_R&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=0&ist=&jit=&bdtype=0&gsm=0&objurl=http%3A%2F%2Fimg.gewara.cn%2Fuserfiles%2Fimage%2F201204%2Fs_1f3ceffd_136823f0f26__7b7c.jpg");
                           InputStream in = url.openStream();
                           System.out.println(in);
                           Bitmap bitmap = BitmapFactory.decodeStream(in);
                           Message msg = handler.obtainMessage(LOAD_SUCCESS, bitmap);
                           handler.sendMessage(msg);
                       } catch (MalformedURLException e) {
                           e.printStackTrace();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
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
