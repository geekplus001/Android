package com.ben.appcontentshare;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem item = menu.findItem(R.id.shared);
        ShareActionProvider sap = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"今天你duang了吗");


        sap.setShareIntent(intent);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_setting)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //分享简单的文本内容
    public void sharedSimpleContentClick(View v)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "你是猪吗");
//        startActivity(intent);
        //指定选择器
        startActivity(Intent.createChooser(intent, "抢红包啦！"));
    }

    //分享图片内容
    public void sharedImageContentClick(View v)
    {
        Uri uri = Uri.parse("/sdcard/1.jpg");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM,uri );
        startActivity(intent);
    }

    //分享多个内容
    public void sharedMultipleContentClick(View v)
    {
        Uri uri1 = Uri.parse("/sdcard/2.jpg");
        Uri uri2 = Uri.parse("/sdcard/3.jpg");
        ArrayList<Uri> list = new ArrayList<>();
        list.add(uri1);
        list.add(uri2);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, list);
        startActivity(intent);
    }

}
