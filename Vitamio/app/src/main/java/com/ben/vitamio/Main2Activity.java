package com.ben.vitamio;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class Main2Activity extends AppCompatActivity {

    private VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Vitamio.isInitialized(getApplicationContext());

        vv = (VideoView) findViewById(R.id.surface_view);
        MediaController mc = new MediaController(this);
        vv.setMediaController(mc);

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"*.flv";
        vv.setVideoPath(path);
        vv.requestFocus();
        vv.start();

    }
}
