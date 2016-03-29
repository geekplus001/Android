package com.ben.telephonymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        testTelephonyManager();
    }


    //电话服务管理器的API方法
    private void testTelephonyManager()
    {
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        System.out.println("电话状态="+tm.getCallState());
        System.out.println("唯一的设备ID="+tm.getDeviceId());
        System.out.println("设备的软件版本号="+tm.getDeviceSoftwareVersion());
        System.out.println("手机号="+tm.getLine1Number());
        System.out.println("获取ISO=标准的国家码，即国际长途区号="+tm.getNetworkCountryIso());
        System.out.println("当前使用的网络类型="+tm.getNetworkType());
        System.out.println("手机类型="+tm.getPhoneType());
        System.out.println("SIM的状态信息="+tm.getSimState());
        System.out.println("唯一的用户ID="+tm.getSubscriberId());
        System.out.println("SIM卡的序列号=" + tm.getSimSerialNumber());
        System.out.println("服务商名称=" + tm.getSimOperatorName());

//        tm.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);

        //测试来电显示：直接发送一个广播
        sendBroadcast(new Intent(this,PhoneListenerReceiver.class));
    }




    /*
    电话服务的监听器
     */
    private static class MyPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state){
                case TelephonyManager.CALL_STATE_RINGING:
                    System.out.println("正在响铃。。。。。。。。。。");
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    System.out.println("挂机状态。。。。。。。。。。");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("正在接听电话..........");
                    break;
            }
        }
    }
}


