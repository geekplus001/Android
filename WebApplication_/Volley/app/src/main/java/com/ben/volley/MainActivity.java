package com.ben.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.LruCache;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue = null;
    private ImageView imageView;
    private NetworkImageView networkImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建一个请求队列
        queue = Volley.newRequestQueue(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);

        networkImageView();



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

    //通过networkImageView组件加载图片
    private void networkImageView() {
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);

        String url = "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&pn=78&spn=0&di=0&pi=23702313188&rn=1&tn=baiduimagedetail&istype=&ie=utf-8&oe=utf-8&in=3354&cl=2&lm=-1&st=&cs=2343546950%2C1642560871&os=&simid=&adpicid=0&ln=31000&fmq=1378374347070_R&ic=0&s=0&se=&sme=&tab=&face=&ist=&jit=&statnum=girl&cg=girl&bdtype=-1&objurl=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6d81800a19d8bc3e55adf77e878ba61ea9d345d9.jpg&fromurl=http%3A%2F%2Fwww.iyi8.com%2F2015%2Fsexy_0510%2F2510_13.html&gsm=";


        networkImageView.setImageUrl(url,new ImageLoader(queue,new BitmapCache()));
    }


    //发送一个字符串的请求
    public void sendStringRequestClick(View view)
    {
        String url = "http://www.baidu.com";
        //创建一个字符串请求（请求方式，url，响应的回调接口，错误的回调接口）
        StringRequest request = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println(volleyError);
                    }
                });
        queue.add(request);

    }

    //发送一个带参数的post请求(请求防护四，url，请求参数，响应的回调接口，错误的回调接口)
    public void sendParamPostRequestClick(View view)
    {
        String url = "http://10.0.2.2:8080/Android_XmlServer/XmlServlet";

        //请求参数封装为jsonObject
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("name","ben");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        System.out.println(s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name","ben");
                return params;
            }
        };
        queue.add(request);
    }

    //发送一个jsonRequest
    public void sendJsonRequestClick(View view)
    {
        String url = "file:///C:/Users/Administrator/Desktop/json.html";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                System.out.println(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError);
            }
        });
        queue.add(request);
    }


    //发送一个ImageRequset
    public void sendImageRequestClick(View view)
    {
        String url = "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&pn=78&spn=0&di=0&pi=23702313188&rn=1&tn=baiduimagedetail&istype=&ie=utf-8&oe=utf-8&in=3354&cl=2&lm=-1&st=&cs=2343546950%2C1642560871&os=&simid=&adpicid=0&ln=31000&fmq=1378374347070_R&ic=0&s=0&se=&sme=&tab=&face=&ist=&jit=&statnum=girl&cg=girl&bdtype=-1&objurl=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6d81800a19d8bc3e55adf77e878ba61ea9d345d9.jpg&fromurl=http%3A%2F%2Fwww.iyi8.com%2F2015%2Fsexy_0510%2F2510_13.html&gsm=";
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                  imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(";;;;;;;;;;;;;;;;;;;;;");
            }
        });
        queue.add(request);
    }



    //使用ImageLoader加载图片
    public void imageLoaderClick(View view)
    {
        String url = "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&pn=78&spn=0&di=0&pi=23702313188&rn=1&tn=baiduimagedetail&istype=&ie=utf-8&oe=utf-8&in=3354&cl=2&lm=-1&st=&cs=2343546950%2C1642560871&os=&simid=&adpicid=0&ln=31000&fmq=1378374347070_R&ic=0&s=0&se=&sme=&tab=&face=&ist=&jit=&statnum=girl&cg=girl&bdtype=-1&objurl=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6d81800a19d8bc3e55adf77e878ba61ea9d345d9.jpg&fromurl=http%3A%2F%2Fwww.iyi8.com%2F2015%2Fsexy_0510%2F2510_13.html&gsm=";
        ImageLoader imageLoader = new ImageLoader(queue,new BitmapCache()
//                new ImageLoader.ImageCache() {
//                    @Override
//                    public Bitmap getBitmap(String s) {
//                        return null;
//                    }
//
//                    @Override
//                    public void putBitmap(String s, Bitmap bitmap) {
//
//                    }
//                }
        );

        //获取图片监听器
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, listener, 200, 200);
    }


    //用于图片缓存
    private class BitmapCache implements ImageLoader.ImageCache{

        private LruCache<String,Bitmap> cache;
        private int maxCache = 10*1024*1024;
        public BitmapCache()
        {
            cache = new LruCache<>(maxCache);
        }

        @Override
        public Bitmap getBitmap(String s) {
            return cache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            cache.put(s,bitmap);
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
