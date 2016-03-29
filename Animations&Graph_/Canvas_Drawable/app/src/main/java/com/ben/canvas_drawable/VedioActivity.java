package com.ben.canvas_drawable;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class VedioActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setFixedSize(320,220);//显示分辨率（不为默认）
    }

    public void playClick(View view)
    {
        mediaPlayer.start();
    }

    public void pauseClick(View view)
    {
        mediaPlayer.pause();
    }

    public void stopClick(View view)
    {
        mediaPlayer.stop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(holder);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/a.mp4";
        try {
            //设置播放来源
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mediaPlayer!=null)
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
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
