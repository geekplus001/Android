package com.ben.apachehttpclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void getClick(View view)
    {
        getRequest();
    }

    //使用apache HttpClient的GET请求
    public void getRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.0.2.2:8080/Android_NetServlet/LoginServlet?username=ben&password=123";
                //创建请求对象
//                Android6.0  API23 谷歌推荐使用HttpURLConnection HttpGet需要导包（builder.gradle(Module)）
                HttpGet get = new HttpGet(path);
                //创建HTTP客户端对象，用于发送请求
                HttpClient httpClient = new DefaultHttpClient();
                try {
                    //向服务器发送请求,并返回响应对象
                    HttpResponse response = httpClient.execute(get);
                    //获取响应的状态码
                    int status = response.getStatusLine().getStatusCode();
                    switch (status){
                        case HttpStatus.SC_OK:
                            //200
                            HttpEntity entity = response.getEntity();
                            String result = EntityUtils.toString(entity,"UTF-8");
                            System.out.println(result);
                            break;
                        case HttpStatus.SC_NOT_FOUND:
                            //404

                            break;
                        case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                            //500
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public void postClick()
    {
        postRequest();
    }

    private void postRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.0.2.2:8080/Android_NetServlet/LoginServlet";
                //创建请求对象
                HttpPost post = new HttpPost(path);

                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username","ben"));
                params.add(new BasicNameValuePair("password","123"));
                try {
                    HttpEntity entity = new UrlEncodedFormEntity(params);
                    post.setEntity(entity);

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpResponse response = httpClient.execute(post);
                    switch (response.getStatusLine().getStatusCode())
                    {
                        case HttpStatus.SC_OK:
                            String result = EntityUtils.toString(response.getEntity());
                            System.out.println(result);
                            break;
                        case HttpStatus.SC_NOT_FOUND:
                            Log.i("HttpClient","404");
                            break;
                        case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                            Log.i("HttpClient","500");
                            break;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
