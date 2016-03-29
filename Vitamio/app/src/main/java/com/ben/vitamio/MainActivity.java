package com.ben.vitamio;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Vitamio.isInitialized(getApplicationContext());

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
