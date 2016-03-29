package com.ben.documentmanage;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
/*
getFreeSpace();
getTotalSpace();
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void writePrivateFileClick(View v)
    {
        try {
            OutputStream out = openFileOutput("ben.txt", Context.MODE_PRIVATE);
            String info = "我是中国人";
            byte[] bytes = info.getBytes();
            out.write(bytes,0,bytes.length);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPrivateFileClick(View v)
    {
        try {
            InputStream in = openFileInput("ben.txt");
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            int len = -1;
            while((len=in.read(bytes))!=-1)
            {
                sb.append(new String(bytes,0,len));

            }
            in.close();
            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //原生文件，只可读
    public void readRAWFileClick(View v)
    {
        try {
            InputStream in = getResources().openRawResource(R.raw.aaa);
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            int len = -1;
            while((len=in.read(bytes))!=-1)
            {
                sb.append(new String(bytes,0,len));

            }
            in.close();
            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //写入一个缓存文件
    public void writePrivateCacheDataClick(View v)
    {

//        String temp = getCacheDir()+"/temp.tmp";
        try {
            //创建一个缓存的文件
            File temp = File.createTempFile("temp",null,getCacheDir());
            FileOutputStream out = new FileOutputStream(temp);
            PrintStream ps = new PrintStream(out);
            ps.print("我可是要成为编程王的男人啊");
            ps.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//是否存在SDCard
    public void idSDCardClick(View v)
    {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(this, "有sd卡", Toast.LENGTH_SHORT).show();
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            {
                Toast.makeText(this, "只读的sd卡", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //sdcard路径
                System.out.println(Environment.getExternalStorageDirectory().getPath());
                //访问安卓内置特定的文件夹
                System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            }
        }
        else
        {
            Toast.makeText(this, "无sd卡", Toast.LENGTH_SHORT).show();
        }
    }

    //写入SDCard 私有文件
    public void writeSDCardPrivateFileClick(View v)
    {
        File file = getExternalFilesDir(null);
        if(file!=null)
        {
            try {
                FileOutputStream out = new FileOutputStream(file+"/ben.txt");
                PrintStream ps = new PrintStream(out);
                ps.print("I am a good boy!");
                ps.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //写入SDCard 私有缓存文件
    public void writeSDCardCachePrivateFileClick(View v)
    {
        try {
            File temp = File.createTempFile("ben",null,getExternalCacheDir());
            if(temp!=null)
            {
                try {
                    FileOutputStream out = new FileOutputStream(temp+"/ben.txt");
                    PrintStream ps = new PrintStream(out);
                    ps.print("I am a good boy!");
                    ps.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

