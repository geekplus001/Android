package com.ben.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import android.view.View;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textView_content;
    private TextView textView_start;
    private TextView textView_temp;
    float ringValue =  40.0f;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_content = (TextView) findViewById(R.id.content);
        textView_start = (TextView) findViewById(R.id.textView_start);
        textView_temp = (TextView) findViewById(R.id.textView_temp);
        System.out.println("-------------------------哈哈哈哈哈哈哈");
        //获取系统传感器管理器
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //通过传感器管理器获取本机所有的传感器
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s:sensors)
        {
            System.out.println(s.toString());
        }

        //获取指定的某一个传感器(摇一摇)
        Sensor type_accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        if(type_accelerometer!=null)
//        {
//            System.out.println(type_accelerometer.toString());
//        }

        //注册传感器的监听器  参数：监听器，要监听的传感器，灵敏度
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //传感器数据变化，在该方法中获取传感器变化的值
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
//                textView_content.setText("x="+x+"y="+y+"z="+z);


                if(Math.abs(x)+Math.abs(y)+Math.abs(z)>=ringValue&&flag==false)
                {
                    flag = true;
                    textView_start.setVisibility(View.VISIBLE);
                    final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.a);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.release();
                            flag = false;
                            textView_start.setVisibility(View.GONE);
                        }
                    });

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //传感器精度的变化
            }
        },type_accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        //温度传感器
        Sensor type_temp = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float temp = event.values[0];
                //小数位往后挪一个  不保留小数位的函数  再除以十就保留一位小数
                temp = (float) (Math.round(temp * 10.0)/10.0);
                textView_temp.setText("温度："+temp+"℃");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },type_temp,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
