package com.ben.media_playback;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener{

    private MediaPlayer mediaPlayer;
    private Button button_play,button_pause,button_playprevious,button_playnext;
    private int index = 0;//当前歌曲索引
    private ArrayList<String> musicList = new ArrayList<>();
    private boolean isPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initMusic();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnErrorListener(this);

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

    private void initMusic()
    {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        musicList.add(path + "1.mp3");
        musicList.add(path + "2.mp3");
        musicList.add(path + "3.mp3");
        musicList.add(path + File.separator + "4.mp3");
    }

    public void initView()
    {
        button_play = (Button) findViewById(R.id.button4_play);
        button_pause = (Button) findViewById(R.id.button6_pause);
        button_playprevious = (Button) findViewById(R.id.button5_playprevious);
        button_playnext = (Button) findViewById(R.id.button7_playnext);

        button_play.setOnClickListener(this);
        button_pause.setOnClickListener(this);
        button_playprevious.setOnClickListener(this);
        button_playnext.setOnClickListener(this);
    }
//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        switch ()
//    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button4_play:
                play();
                break;
            case R.id.button6_pause:
                pause();
                break;
            case R.id.button5_playprevious:
                previous();
                break;
            case R.id.button7_playnext:
                next();
                break;
        }
    }

    private void next() {
        if(index + 1<musicList.size())
        {
            index++;
        }
        else
        {
            index  = 0;
        }
        start();
    }

    private void previous() {
        if(index-1>=0)
        {
            index--;
        }
        else
        {
            index = musicList.size() - 1;
        }
        start();
    }

    private void pause() {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            isPause = true;
        }
    }


    private void play() {
        if(isPause)
        {
            mediaPlayer.start();
            isPause  = false;
        }
        else
        {
            start();
        }

    }
    //从头开始播放音乐
    private void start()
    {
        if(index<musicList.size())
        {
            if(mediaPlayer.isPlaying())
                mediaPlayer.stop();
            String musicPath = musicList.get(index);
            try {
                mediaPlayer.setDataSource(musicPath);
                mediaPlayer.prepareAsync();
                isPause  = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mediaPlayer.reset();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null)
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }
}
