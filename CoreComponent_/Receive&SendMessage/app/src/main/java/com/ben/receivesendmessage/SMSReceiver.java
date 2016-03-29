package com.ben.receivesendmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Objects;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            Object[] objs = (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessages = new SmsMessage[objs.length];
            for(int i=0;i<objs.length;i++)
            {

                smsMessages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
                //发送方的号码
                String number = smsMessages[i].getDisplayOriginatingAddress();
                //短信的内容
                String content = smsMessages[i].getDisplayMessageBody();
                Toast.makeText(context,number+"--"+content,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
