package com.ben.media_playback;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

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

    //播放一
    public void playfromRes(View view)
    {
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.a);
        mediaPlayer.start();
    }
    //播放二
    public void playfromSys(View view)
    {
        MediaPlayer mediaPlayer = new MediaPlayer();
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/*.mp3";
        try {
            mediaPlayer.setDataSource(this, Uri.parse(path));
            mediaPlayer.prepare();//读完再播放
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //播放三
    public void playfromNet(View view)
    {
        String path = "http://www.kuwo.cn/yinyue/291598/";
         final MediaPlayer mediaPlayer = new MediaPlayer();


        try {
            mediaPlayer.setDataSource(this, Uri.parse(path));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
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
