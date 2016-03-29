package com.ben.receivesendmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessageClick(View v)
    {
        //获取短信管理器
        SmsManager smsManager = SmsManager.getDefault();
        String message = "hahahha~~~";
        //拆分长短信
        ArrayList<String> list = smsManager.divideMessage(message);
        System.out.println("list.size="+list.size());
        for(int i=0;i<list.size();i++)
        {
            smsManager.sendTextMessage("18039223258",null,list.get(i),null,null);
        }
    }
}
