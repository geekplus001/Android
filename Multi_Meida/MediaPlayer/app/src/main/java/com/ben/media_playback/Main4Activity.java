package com.ben.media_playback;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void queryClick(View view)
    {
        ContentResolver contentProvider = getContentResolver();
        Cursor cursor = contentProvider.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if(cursor!=null)
        {
            while(cursor.moveToNext())
            {
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String songName = cursor.getColumnName(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String artist  = cursor.getColumnName(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String time = cursor.getColumnName(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                System.out.println(url);
                System.out.println(songName);
                System.out.println(artist);
                System.out.println("-----------------");
            }
        }
    }
}
