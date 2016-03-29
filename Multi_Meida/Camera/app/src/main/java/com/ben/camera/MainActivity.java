package com.ben.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case RESULT_OK:
                if(MEDIA_TYPE_IMAGE==requestCode)
                {
//                    Uri uri = data.getData();
//                    System.out.println(uri.toString());
                    System.out.println(file);
                }
                else if(MEDIA_TYPE_VIDEO==requestCode)
                {
                    System.out.println(file);
                    System.out.println(data.getData());
                }
                break;
        }
    }

    public void cameraClick(View view)
    {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //此句导致data得不到
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputMediaFileUri(MEDIA_TYPE_IMAGE));
        startActivityForResult(intent,MEDIA_TYPE_IMAGE);
    }

    public void vedioClick(View view)
    {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputMediaFileUri(MEDIA_TYPE_VIDEO));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent, MEDIA_TYPE_VIDEO);
    }

    File file = null;

    private Uri getOutputMediaFileUri(int type)
    {
        file = getOutputMediaFile(type);
        return Uri.fromFile(file);
    }

    private File getOutputMediaFile(int type)
    {
        File file = null;
        String rootPath = null;
        switch(type)
        {
            case MEDIA_TYPE_IMAGE:
                rootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
                file = new File(rootPath+File.separator+System.currentTimeMillis()+".jpg");
                break;

            case MEDIA_TYPE_VIDEO:
                rootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
                file = new File(rootPath+File.separator+System.currentTimeMillis()+".mp4");
                break;

        }
        return  file;
    }

}
