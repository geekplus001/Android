package com.ben.supportingmultiplescreens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕的高和宽
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;

        float density = dm.density;
        int dpi = dm.densityDpi;

        System.out.println("heightPixels="+heightPixels);
        System.out.println("widthPixels="+widthPixels);
        System.out.println("density="+density);
        System.out.println("dpi="+dpi);
    }
}
