package com.ben.android_async_http;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

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

    //发送一个简单的GET请求
    public void sendSimpleGetClick(View view)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.baidu.com", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                //200 ok
                String info = new String(bytes);
                System.out.println(info);
            }

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println("error:" + new String(bytes));
            }
        });
    }


    //发送一个带参数的请求
    public void sendParamClick(View view)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username","ben");
        params.put("password", "123");
        client.post(this, "http://10.0.2.2:8080/Android_NetServlet/LoginServlet", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                System.out.println(new String(bytes));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println(new String(bytes));
            }

        });
//        HashMap<String,String> paramMap = new HashMap<>();
//        paramMap.put("key","value");
//        RequestParams params1 = new RequestParams(paramMap);
    }

    public void uploadClick(View view)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("description","美女图片");
        //设置文件
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/c.jpg";
            params.put("c.jpg",new File(path),"application/x-jpg");//"image/jpeg"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        client.post(this, "http:10.0.2.2:8080/ServletUpload/UploadServlet", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                System.out.println(new String(bytes));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println(new String(bytes));
            }
        });
    }


    public void downloadClick(View view)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://img.taopic.com/uploads/allimg/111225/9115-11122514225810.jpg", new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, File file) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, File file) {
                System.out.println(file.getAbsolutePath());
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "9115-11122514225810.jpg";
                try {
                    InputStream in = new FileInputStream(file);
                    OutputStream out = new FileOutputStream(path);
                    byte[] bytes = new byte[100];
                    int len = -1;
                    while ((len = in.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                        out.flush();
                    }
                    out.close();
                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("download success");
            }
        });

    }

    public void jsonClick(View view) throws UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://10.0.2.2:8080/Android_NetServlet/LoginServlet";
        JSONObject jsonObject  = new JSONObject();
        try {
            jsonObject.put("username","ben");
            jsonObject.put("password","123");

            StringEntity entity = new StringEntity(jsonObject.toString());
            client.post(this,url, entity,"application/json",new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    System.out.println(response.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
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
