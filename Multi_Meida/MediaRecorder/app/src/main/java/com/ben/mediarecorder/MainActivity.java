package com.ben.mediarecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaRecorder.OnErrorListener{

    MediaRecorder mr;
    private static  int flag = 0;
    private Button btnrecorder,btnstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnrecorder = (Button) findViewById(R.id.button);
        btnstop = (Button) findViewById(R.id.button2);
        mr = new MediaRecorder();
        init();
    }

    private void init() {
        mr.reset();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音源
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS)+ File.separator+System.currentTimeMillis()+".mp3";
        mr.setOutputFile(path);
        try {
            mr.prepare();
            flag = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnstop.setEnabled(false);
    }

    public void recorderClick(View view)
    {
        if(flag == 1)
        {
            mr.start();
        }
        btnstop.setEnabled(true);
        btnrecorder.setEnabled(false);
    }

    public void stopClick(View view)
    {
        mr.stop();
        btnrecorder.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mr!=null)
        {
            mr.release();
        }
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        mr.reset();
    }
}
