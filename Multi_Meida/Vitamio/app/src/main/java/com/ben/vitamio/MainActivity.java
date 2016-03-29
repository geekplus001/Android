package com.ben.vitamio;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;

import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //加载so类库（通常使用C、C++编写类库）
        if (!LibsChecker.checkVitamioLibs(this))
            return;


        initMediaPlayer();


        setContentView(R.layout.activity_main);
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer(this);
    }

    public void play(View view)
    {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/*.mp3";
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pause(View view)
    {
        mediaPlayer.pause();
    }
    public void stop(View view)
    {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
