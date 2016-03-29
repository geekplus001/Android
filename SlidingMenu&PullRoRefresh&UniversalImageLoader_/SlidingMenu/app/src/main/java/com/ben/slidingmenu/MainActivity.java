package com.ben.slidingmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("hahhhaahahahaahhahahaahahahahaahahah");
        //创建侧滑菜单
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.55f);
        menu.setMenu(R.layout.menu_layout);
        menu.setBehindScrollScale(0.0f);
        menu.setBehindOffsetRes(R.dimen.menu_offset);//设置相对屏幕的偏移量
        menu.setBackgroundColor(Color.BLUE);
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
    }
}
