package com.ben.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0x1;
    private NfcAdapter nfcAdapter;
    private String packageName;
    private PendingIntent pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null)
        {
            Toast.makeText(this,"设备不支持NFC功能",Toast.LENGTH_SHORT).show();
            finish();
        }
        //屏幕亮着NFC才会起作用

        //判断设备是否打开NFC功能
        else if(!nfcAdapter.isEnabled())
        {
            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
            startActivity(intent);
        }

        pi = PendingIntent.getActivity(this,0,new Intent(this,getClass()),0);
    }

    //
    public void selectStartAppClick(View view)
    {
        Intent intent = new Intent(this,PackageListActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode == RESULT_OK)
        {
            packageName = data.getStringExtra("packageName");
            Toast.makeText(MainActivity.this,packageName,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isRead = true;//true 表示读取 false 为写入
    //写入状态
    public void writeClick(View view)
    {
        isRead = false;
        Toast.makeText(MainActivity.this,"当前为写入状态",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
       if(nfcAdapter!=null)
       {
           //设置当前程序为优先处理NFC的程序
           nfcAdapter.enableForegroundDispatch(this,pi,null,null);
       }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消优先设置
        if(nfcAdapter!=null)
        {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        write(intent);
    }

    //写入方法
    private void write(Intent intent)
    {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(tag == null)
        {
            return;
        }
        //写入数据
        //启动指定应用程序
//        NdefRecord[] records = new NdefRecord[]{NdefRecord.createApplicationRecord(packageName)};
        //打开Uri
        NdefRecord[] records = new NdefRecord[]{NdefRecord.createUri("http://www.baidu.com")};
        NdefMessage ndefMessage = new NdefMessage(records);

        Ndef ndef = Ndef.get(tag);
        if(ndef!=null)
        {
            try {
                ndef.connect();

                if(ndef.isWritable())
               {
                   int size = ndefMessage.toByteArray().length;
                   if(ndef.getMaxSize()>size)
                    {
                       ndef.writeNdefMessage(ndefMessage);
                    }
               }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this,"写入成功",Toast.LENGTH_SHORT).show();
        }
        else
        {
            try {
                NdefFormatable ndefFormatable = NdefFormatable.get(tag);
                if(ndefFormatable!=null)
                {
                    ndefFormatable.connect();
                    ndefFormatable.format(ndefMessage);//格式化并写入数据
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
        }
    }
}
