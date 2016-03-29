package com.ben.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.UUID;
import android.view.View;
import android.widget.Toast;

public class ServerBluetoothActivity extends AppCompatActivity {

    private static final int CONN_SUCCESS = 0X1;
    private static final int CONN_FAIL = 0X2;
    private static final int RECEIVER_INFO = 0X3;
    private static final int SET_EDITTEXT_NULL = 0X4;
    private Button button_send;
    private TextView textView_content;
    private EditText editText_info;

    BluetoothAdapter bluetoothAdapter = null;//本地蓝牙设备
    BluetoothServerSocket serverSocket = null;//蓝牙设备Socket服务端
    BluetoothSocket socket = null;//蓝牙设备Socket客户端

    //输入输出流
    PrintStream out;
    BufferedReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_bluetooth);

        setTitle("蓝牙服务端");
        textView_content = (TextView) findViewById(R.id.textView_content);
        editText_info = (EditText) findViewById(R.id.editText_info);
        button_send = (Button) findViewById(R.id.button_send);
        init();
    }

    //创建蓝牙服务器端的Socket
    private void init() {
        textView_content.setText("服务器端已启动，正在等待连接...\n");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //得到本地设备
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                //创建蓝牙Socket连接
                try {
                    serverSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("test", UUID.fromString("00000000-2527-eef3-ffff-fffffe3160865"));
                    //阻塞等待Socket客户端请求
                    socket = serverSocket.accept();
                    if(socket!=null)
                    {
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
    /*
    接受消息的线程
     */
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
            Toast.makeText(this,"不能发送空消息",Toast.LENGTH_SHORT).show();
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
