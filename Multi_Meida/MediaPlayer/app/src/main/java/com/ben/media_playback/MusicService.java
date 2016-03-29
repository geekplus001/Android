package com.ben.media_playback;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.IBinder;
import android.os.PowerManager;

import java.io.File;
import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener,AudioManager.OnAudioFocusChangeListener{

    public MediaPlayer mediaPlayer;
    public static final String ACTION_PLAY = "com.ben.ACTION_PLAY";
    public static final String ACTION_PAUSE = "com.ben.ACTION_PAUSE";
    public static final String ACTION_EXIT = "com.ben.ACTION_EXIT";
    public static final String ACTION_STOP = "com.ben.ACTION_STOP";
    private  WifiManager.WifiLock lock;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //获取音频的焦点
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        am.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);



    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        //保证cpu正常  第一个参数：整个应用程序的上下文
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        //保持WIFI不被休眠（黑屏后）
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        lock  = wifiManager.createWifiLock("mylock");
        lock.acquire();

        mediaPlayer.setOnPreparedListener(this);
        notification();
    }

    ////退出去后发一个通知出来
    private void notification() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("我的");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("音乐神器");
        builder.setContentInfo("正在播放");
        PendingIntent pi = PendingIntent.getActivity(this,0,new Intent(this,Main3Activity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        startForeground(0,notification);
        nm.notify(0,notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        lock.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if(ACTION_PLAY.equals(action))
        {
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+ File.separator+"beyond-hktk,mp3");
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(ACTION_PAUSE.equals(action))
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
            }
        }
        else if(ACTION_EXIT.equals(action))
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        else if(ACTION_STOP.equals(action))
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange)
        {
            case AudioManager.AUDIOFOCUS_GAIN:
                initMediaPlayer();
                mediaPlayer.setVolume(1.0f,1.0f);//设置音量为一半
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                //长期失去焦点
                if(mediaPlayer!=null)
                {
                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.stop();
                    }
                    mediaPlayer.release();
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                //失去焦点但很快会获取
                if(mediaPlayer!=null)
                {
                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.stop();
                    }
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                //允许低音量播放
                if(mediaPlayer.isPlaying())
                    mediaPlayer.setVolume(0.1f,0.1f);
                break;
        }
    }
}
