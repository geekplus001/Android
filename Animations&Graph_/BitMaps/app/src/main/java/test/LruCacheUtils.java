package test;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import libcore.io.DiskLruCache;

/**
 * Created by Administrator on 2016/3/6 0006.
 */
public class LruCacheUtils {
    private static LruCacheUtils lruCacheUtils;

    private DiskLruCache diskLruCache;//LRU磁盘缓存
    private LruCache<String,Bitmap> lruCache;//LRU内存缓存
    private Context context;


    private LruCacheUtils(){}

    public static LruCacheUtils getInstance()
    {
        if(lruCacheUtils ==null)
        {
            lruCacheUtils = new LruCacheUtils();
        }
        return lruCacheUtils;
    }

    //打开磁盘缓存
    public void open(Context context,String disk_cache_subdir,int disk_cache_size)
    {

        try {
            this.context = context;
            //获取当前Activity内存大小
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            int memoryClass = activityManager.getMemoryClass();
            lruCache = new LruCache<>(1024*1024*memoryClass/8);
            //参数：缓存目录，版本，一个key（1）存一个文件，磁盘缓存大小（10MB一般）
            diskLruCache = DiskLruCache.open(getCacheDir(disk_cache_subdir),getAppVersion(),1,disk_cache_size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取缓存目录
    private File getCacheDir(String name)
    {
        String cachePath = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !Environment.isExternalStorageRemovable()?
                context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();
        return new File(cachePath + File.separator + name);
    }

    private int getAppVersion()
    {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    //一下连个方法为计算文件key所需要（MD5）
    public String hashKeyForDisk(String key)
    {
        String cacheKey;
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(key.getBytes());
            cacheKey = bytesToHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }
    public String bytesToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length() == 1)
            {
                sb.append(hex);
            }
            sb.append(hex);
        }
        return sb.toString();
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

    //位图重新采样（2）
    public Bitmap decodeSampledBitMapFromStream(byte[] bytes,int reqWidth,int reqHeight)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        options.inSampleSize = calculateInsampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    //添加图片缓存
    public void addBitmapCache(String url,Bitmap bitmap)
    {
        String key = hashKeyForDisk(url);

        if(getBitmapFromCache(key)==null)
        {
            System.out.println("key="+key);
            System.out.println("bitmap="+bitmap);
            lruCache.put(key,bitmap);
        }
    }
    //取图片缓存
    public Bitmap getBitmapFromCache(String url)
    {
        String key = hashKeyForDisk(url);
        return lruCache.get(key);
    }


    public void putCache(final String url,final CallBack callback)
    {
        new AsyncTask<String,Void,Bitmap>()
        {

            @Override
            protected Bitmap doInBackground(String... params) {
                String key = hashKeyForDisk(params[0]);
                System.out.println("key="+key);
                DiskLruCache.Editor editor = null;
                Bitmap bitmap = null;
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(1000 * 30);
                    conn.setConnectTimeout(1000*30);
                    ByteArrayOutputStream baos = null;
//                    editor = diskLruCache.edit(key);
                    if(conn.getResponseCode()==HttpURLConnection.HTTP_OK)
                    {
                        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                        baos = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while((len=bis.read(bytes))!=-1)
                        {
                            baos.write(bytes,0,len);
                        }
                        bis.close();
                        baos.close();
                        conn.disconnect();
                    }
                    if(baos!=null)
                    {
                        bitmap = decodeSampledBitMapFromStream(baos.toByteArray(),100,100);
                        addBitmapCache(params[0],bitmap);
                        editor = diskLruCache.edit(key);
                        System.out.println(url.getFile());
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,editor.newOutputStream(0));
                        editor.commit();
                    }
//                    BufferedOutputStream out = new BufferedOutputStream(editor.newOutputStream(0),8*1024);
//                    BufferedInputStream in = new BufferedInputStream(conn.getInputStream(),8*1024);
//                    byte [] bytes = new byte[1024];
//                    int len = -1;
//                    while((len=in.read(bytes))!=-1)
//                    {
//                        out.write(bytes,0,len);
//                    }
//                    out.close();
//                    in.close();
//                    editor.commit();
//                    conn.disconnect();
                } catch (IOException e) {

                    try {
                        editor.abort();//放弃写入
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                return bitmap;
            }
            @Override
            protected void  onPostExecute(Bitmap bitmap)
            {
                super.onPostExecute(bitmap);
                callback.response(bitmap);
            }
        }.execute(url);

    }
    public interface CallBack<T>
    {
        public void response(T entity);
    }

    //
    public InputStream getDiskCache(String url)
    {
        String key = hashKeyForDisk(url);
        System.out.println("getDiskCache="+key);
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            System.out.println(snapshot);
            if(snapshot!=null)
            {
                return snapshot.getInputStream(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭磁盘缓存
    public void close()
    {
        if(diskLruCache!=null && !diskLruCache.isClosed())
        {
            try {
                diskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //刷新磁盘缓存
    public void flush()
    {
        if(diskLruCache!=null)
        {
            try {
                diskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
