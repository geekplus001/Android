package com.ben.httpurlconnection;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpURLConnectionActivity extends AppCompatActivity {


    private static final int LOAD_SUCCESS = 200;
    private static final int LOAD_ERROR = -1;
    private final MyHandler handler = new MyHandler(this);

    private TextView textView;
    private EditText et_username,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);


        textView = (TextView) findViewById(R.id.textView);
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);


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



    private static class MyHandler extends Handler {

        private final WeakReference<HttpURLConnectionActivity> weakReference;

        private MyHandler(HttpURLConnectionActivity activity) {
            weakReference = new WeakReference<HttpURLConnectionActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HttpURLConnectionActivity activity = weakReference.get();
            if(activity!=null)
            {
                switch (msg.what){
                    case LOAD_SUCCESS:
                        String json = (String) msg.obj;
                        activity.jsonToObject(json);
                        break;
                    case LOAD_ERROR:
                        activity.textView.setText("用户名或密码错误");
                        break;
                }
            }
        }
    }


    //解析JSON
    public void jsonToObject(String json)
    {
        Gson gson = new Gson();

        JsonObject object = gson.fromJson(json, JsonObject.class);
        textView.setText(object.toString());
    }

    //登陆功能
    public void loginClick(View view)
    {
        //下边的匿名线程要访问，需要final
        final String username = et_username.getText().toString();
        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        final String password = et_password.getText().toString();
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //启动登陆工作线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.0.2.2:8080/Android_NetServlet/LoginServlet";
                try {
                    URL url = new URL(path);
                    //打开http链接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(1000 * 30);//链接超时时间
                    conn.setReadTimeout(1000 * 30);//读数据的超时时间
                    conn.setUseCaches(false);//不能使用缓存
                    conn.setRequestProperty("content-type","application/x-www-form-urlencoded");//设置请求属性
                    //对服务器端读取或写入数据（使用输入输出流）
                    //获取链接的输出流
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes("username="+ URLEncoder.encode("ben","UTF-8"));
                    out.writeBytes("&password="+URLEncoder.encode("123","UTF-8"));
                    out.flush();
                    out.close();
                    //从服务器获取响应数据
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String result = br.readLine();
                    System.out.println(result);
                    br.close();
                    conn.disconnect();//关闭链接
                    Message msg = handler.obtainMessage(LOAD_SUCCESS,result);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(LOAD_ERROR);
                }
            }
        }).start();
    }
}

