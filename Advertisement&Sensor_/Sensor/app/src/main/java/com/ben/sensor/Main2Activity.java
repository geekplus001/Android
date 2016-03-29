package com.ben.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Main2Activity extends Activity {
    private RectView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("444444444444444444444444444444444444");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        rv = new RectView(this,dm.widthPixels,dm.heightPixels);
        setContentView(rv);//自定义View
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor type_gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                rv.rect.setLeft(Math.round(x));
                rv.rect.setTop(Math.round(y));
                rv.invalidate();//重绘

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },type_gravity,SensorManager.SENSOR_DELAY_GAME);
    }
}
