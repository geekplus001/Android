package com.ben.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.UUID;

public class ClientBlutToothActivity extends AppCompatActivity {

    private static final int CONN_SUCCESS = 0X1;
    private static final int CONN_FAIL = 0X2;
    private static final int RECEIVER_INFO = 0X3;
    private static final int SET_EDITTEXT_NULL = 0X4;
    private Button button_send;
    private TextView textView_content;
    private EditText editText_info;

    BluetoothAdapter bluetoothAdapter = null;//本地蓝牙设备

    BluetoothDevice device  = null;//远程蓝牙设备

    BluetoothSocket socket = null;//蓝牙设备Socket客户端

    //输入输出流
    PrintStream out;
    BufferedReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_blut_tooth);

        setTitle("蓝牙客户端");
        textView_content = (TextView) findViewById(R.id.textView_content);
        editText_info = (EditText) findViewById(R.id.editText_info);
        button_send = (Button) findViewById(R.id.button_send);
        init();
    }
    //创建蓝牙客户端的Socket
    private void init() {
        textView_content.setText("正在与服务器端连接...\n");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //得到本地设备
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    //通过本地蓝牙设备得到远程蓝牙设备
                    device = bluetoothAdapter.getRemoteDevice("74:51:BA:F2:80:75");//设备mac
                    //根据UUID 创建并返回一个BluetoothSocket
                    socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00000000-2527-eef3-ffff-fffffe3160865"));
                    if(socket!=null)
                    {
                        //连接
                        socket.connect();

                        out = new PrintStream(socket.getOutputStream());
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    }
                    handler.sendEmptyMessage(CONN_SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message message = handler.obtainMessage(CONN_FAIL,e.getLocalizedMessage());
                    handler.sendMessage(message);
                }
            }
        }).start();

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case RECEIVER_INFO:
                    setInfo(msg.obj.toString()+"\n");
                    break;
                case SET_EDITTEXT_NULL:
                    editText_info.setText("");
                    break;
                case CONN_SUCCESS:
                    setInfo("连接成功！\n");
                    button_send.setEnabled(true);
                    new Thread(new ReceiverInfoThread()).start();
                    break;
                case CONN_FAIL:
                    setInfo("连接失败！\n");
                    break;
                default:
                    break;
            }
        }
    };
    private void setInfo(String info)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(textView_content.getText());
        sb.append(info);
        textView_content.setText(sb);
    }


    private boolean isReceiver = true;
    class ReceiverInfoThread implements Runnable{

        @Override
        public void run() {
            String info = null;
            while(isReceiver)
            {
                try {
                    info = in.readLine();
                    Message msg = handler.obtainMessage(RECEIVER_INFO,info);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void sendClick(View view)
    {
        final String content = editText_info.getText().toString();
        if(TextUtils.isEmpty(content))
        {
            Toast.makeText(this, "不能发送空消息", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                out.println(content);
                out.flush();
                handler.sendEmptyMessage(SET_EDITTEXT_NULL);
            }
        }).start();
    }
}
