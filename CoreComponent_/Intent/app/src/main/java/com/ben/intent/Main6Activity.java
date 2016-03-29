package com.ben.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    //打开网页示例
    public void browserPageClick(View v)
    {
        Uri data = Uri.parse("http://www.baidu.com");
        String action = Intent.ACTION_VIEW;
        Intent intent = new Intent(action,data);
        startActivity(intent);
    }
    //打开拨号面板
    public void callPhoneDialClick(View v)
    {
        Uri data = Uri.parse("tel:123456789");
        String action = Intent.ACTION_DIAL;
        Intent intent = new Intent(action,data);
        startActivity(intent);
    }
    //打开拨号面板
    public void callPhoneClick(View v)
    {
        Uri data = Uri.parse("tel:123456789");
        String action = Intent.ACTION_CALL;
        Intent intent = new Intent(action,data);
        startActivity(intent);
    }
    //打开发短息面板
    public void smsDialClick(View v)
    {
        Intent intent = new Intent();
        String action = Intent.ACTION_VIEW;
        intent.setAction(action);
        intent.putExtra("sms_body","你今天吃了吗");
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
    }
    //发送短信
    public void smsSendClick(View v)
    {
        Uri data = Uri.parse("smsto:123456789");
        String action = Intent.ACTION_SENDTO;
        Intent intent = new Intent(action,data);
        intent.putExtra("sms_body", "一起吃个饭白");
        startActivity(intent);
    }
    //播放音乐
    public void playMusicClick(View v)
    {
        Uri data = Uri.parse("file:///sdcard/Music/xpg.mp3");
        String action = Intent.ACTION_VIEW;
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setDataAndType(data, "audio/mp3");
        startActivity(intent);
    }
    //卸载程序
    public void uninstallClick(View v)
    {
        Uri data = Uri.parse("package:com.ben.activity");
        String action = Intent.ACTION_DELETE;
        Intent intent = new Intent(action,data);
        startActivity(intent);
    }
    //安装程序
    public void installClick(View v)
    {
        Uri data = Uri.fromFile(new File("/sdcard/Download/hello.apk"));
        String action = Intent.ACTION_VIEW;
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setDataAndType(data,"application/vnd.android.package-archive");
        startActivity(intent);
    }
}
