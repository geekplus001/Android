package com.ben.bitmaps;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;

import test.LruCacheUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView,imageView2;
    LruCache<String,Bitmap> lruCache;

    private LruCacheUtils lruCacheUtils;
    private final String DISK_CACHE_SUBDIR = "temp";
    private final int DISK_CACHE_SIZE = 10*1024*1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        //获取当前Activity内存大小
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        final int cacheSize = 1024*1024*memoryClass/8;//八分之一作为图片缓存

        lruCache = new LruCache<>(cacheSize);



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

    //添加图片缓存
    public void addBitmapCache(String key,Bitmap bitmap)
    {
        if(getBitmapFromCache(key)==null)
        {
            lruCache.put(key,bitmap);
        }
    }
    //取图片缓存
    public Bitmap getBitmapFromCache(String key)
    {
        return lruCache.get(key);
    }



    public void showClick(View view)
    {
        String key = String.valueOf(R.mipmap.g);
        Bitmap bitmap = getBitmapFromCache(key);
        if(bitmap==null)
        {
            bitmap = decodeSampledBitMapFromResource(getResources(),R.mipmap.g,100,100);
            addBitmapCache(key,bitmap);
        }
        else
        {
            System.out.println("lruCache已存在");
        }

        imageView.setImageBitmap(bitmap);
    }


    public void show2Click(View view)
    {
        String url = "http://img.1985t.com/uploads/attaches/2014/10/24532-fcw5hR.jpg";
        loadBitmap(url, imageView2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lruCacheUtils = LruCacheUtils.getInstance();
        lruCacheUtils.open(this, DISK_CACHE_SUBDIR, DISK_CACHE_SIZE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lruCacheUtils.flush();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lruCacheUtils.close();
    }
    public void loadBitmap(String url,final ImageView imageView)
    {
        Bitmap bitmap = lruCacheUtils.getBitmapFromCache(url);
        if(bitmap==null)
        {
            InputStream in = lruCacheUtils.getDiskCache(url);
            if(in==null)
            {
                lruCacheUtils.putCache(url, new LruCacheUtils.CallBack<Bitmap>() {
                    @Override
                    public void response(Bitmap entity) {
                        System.out.println("http load");
                        imageView.setImageBitmap(entity);
                    }
                });
            }
            else
            {
                System.out.println("disk cache");
                bitmap = BitmapFactory.decodeStream(in);
                lruCacheUtils.addBitmapCache(url,bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }
        else
        {
            System.out.println("memory cache");
            imageView.setImageBitmap(bitmap);
        }
    }

    //计算位图的采样比例
    public int calculateInsampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight)
    {
        //获取位图的原尺寸
        int w = options.outWidth;
        int h = options.outHeight;
        int inSampleSize = 1;

        if(w>reqWidth || h>reqHeight)
        {
            if(w>h)
            {
                inSampleSize = Math.round((float)h/(float)reqHeight);
            }
            else
            {
                inSampleSize = Math.round((float)w/(float)reqWidth);
            }
        }

        return inSampleSize;
    }

    //位图重新采样
    public Bitmap decodeSampledBitMapFromResource(Resources res,int resid,int reqWidth,int reqHeight)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resid,options);
        options.inSampleSize = calculateInsampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resid,options);
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
