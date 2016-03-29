package com.ben.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

public class MainActivity extends AppCompatActivity {

    private String url = "http://c.hiphotos.baidu.com/image/pic/item/9825bc315c6034a8e47e6d85c913495409237612.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("sssssssssssssssssssssss");
        Log.i("o","ooooooooooooooooooooooooooooooo");
        ImageView image = (ImageView) findViewById(R.id.imageView);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否启用内存
                .cacheOnDisk(true)
//                .displayer(new RoundedBitmapDisplayer(20))//圆角显示
                .displayer(new BitmapDisplayer() {
                    @Override
                    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap1);
                        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                        Paint paint = new Paint();
                        paint.setShader(shader);
                        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, paint);
                        imageAware.setImageBitmap(bitmap1);
                    }
                })
                .build();


//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
//                .memoryCacheSizePercentage(20)//设置缓存占内存百分比
//                .diskCacheFileCount(100)//设置下载图片数  超出了会删除部分
//                .diskCacheSize(5 * 1024 *1024)//设置磁盘缓存
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())//显示的样式
//                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSizePercentage(20)//设置缓存占内存百分比
                .diskCacheFileCount(100)//设置下载图片数  超出了会删除部分
                .diskCacheSize(5 * 1024 *1024)//设置磁盘缓存
                .defaultDisplayImageOptions(options)//显示的样式
                .build();

        //方式一
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
//        ImageLoader.getInstance().displayImage(url,image);
        //方式二
        ImageLoader.getInstance().init(configuration);
//        ImageLoader.getInstance().displayImage(url,image);



        DisplayImageOptions options1 = new DisplayImageOptions.Builder()
                .displayer(new BitmapDisplayer() {
                    @Override
                    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap1);
                        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                        Paint paint = new Paint();
                        paint.setShader(shader);

                        Path path = new Path();
                        path.moveTo(bitmap.getWidth() / 2,0);
                        path.lineTo(bitmap.getWidth() / 4,bitmap.getHeight());
                        path.lineTo(bitmap.getWidth(),bitmap.getHeight() / 3);
                        path.lineTo(0,bitmap.getHeight() / 3);
                        path.lineTo(bitmap.getWidth() / 4 *3,bitmap.getHeight());
//                        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, paint);
                        canvas.drawPath(path,paint);
                        imageAware.setImageBitmap(bitmap1);
                    }
                })
                .build();
        ImageLoader.getInstance().displayImage(url,image,options1);
//        ImageLoader.getInstance().displayImage(url, image, options, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//
//            }
//        }, new ImageLoadingProgressListener() {
//            @Override
//            public void onProgressUpdate(String imageUri, View view, int current, int total) {
//
//            }
//        });

//        ImageLoader.getInstance().loadImage();//只下载不展示
    }
}
