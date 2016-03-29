package com.ben.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBlueToothClick(View view)
    {
        //方式一，提示对话框
//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
//        startActivity(discoverableIntent);
        //方式二，静默打开
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.enable();
    }

    public void closeBlueToothClick(View view)
    {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.disable();
    }

    public void scanClick(View view)
    {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.startDiscovery();//开始扫描附近蓝牙设备
        Set<BluetoothDevice> bluetoothDeviceSet = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice bd:bluetoothDeviceSet)
        {
            System.out.println(bd.toString());
            Toast.makeText(this,"sb",Toast.LENGTH_SHORT).show();
        }
    }

    public void serverClick(View view)
    {
        Intent intent = new Intent(this,ServerBluetoothActivity.class);
        startActivity(intent);
    }

    public void clientClick(View view)
    {
        Intent intent = new Intent(this,ClientBlutToothActivity.class);
        startActivity(intent);
    }
}
