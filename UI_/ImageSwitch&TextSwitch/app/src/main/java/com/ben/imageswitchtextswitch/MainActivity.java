package com.ben.imageswitchtextswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory, View.OnTouchListener{
    private ImageSwitcher imageSwitcher;
    private int[] images = {R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e,
            R.mipmap.f,R.mipmap.g,R.mipmap.h};
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSwitcher  = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(this);
        imageSwitcher.setOnTouchListener(this);
    }

    @Override
    public View makeView() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(images[0]);
        return iv;
    }

    float startX = 0.0f;
    float endX = 0.0f;
    //触屏事件
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();//获取当前事件动作
        if(action == MotionEvent.ACTION_DOWN)
        {
            startX = event.getX();
            return true;
        }
        if(action == MotionEvent.ACTION_UP)
        {
            endX = event.getX();
            if(startX-endX>20)
            {
                index = index+1<images.length?++index:0;
                imageSwitcher.setInAnimation(this,android.R.anim.fade_in);
                imageSwitcher.setInAnimation(this,android.R.anim.fade_out);
            }else if(endX-startX>20)
            {
                index = index-1>=0?--index:images.length-1;
                imageSwitcher.setInAnimation(this,android.R.anim.fade_in);
                imageSwitcher.setInAnimation(this,android.R.anim.fade_out);
            }
            imageSwitcher.setImageResource(images[index]);
        }
        return true;
    }
}
