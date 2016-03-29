package com.ben.imageswitchtextswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Main2Activity extends AppCompatActivity implements ViewSwitcher.ViewFactory,View.OnTouchListener{
    private TextSwitcher textSwitcher;
    String[] texts = {"岁月是把杀猪刀","我爸是李刚","you can you up，no can no BB"};
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        textSwitcher.setOnTouchListener(this);
        textSwitcher.setFactory(this);
    }

    @Override
    public View makeView() {
        TextView tv = new TextView(this);
        tv.setText(texts[index]);
        return tv;
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
                index = index+1<texts.length?++index:0;
                textSwitcher.setInAnimation(this, android.R.anim.fade_in);
                textSwitcher.setInAnimation(this,android.R.anim.fade_out);
            }else if(endX-startX>20)
            {
                index = index-1>=0?--index:texts.length-1;
                textSwitcher.setInAnimation(this,android.R.anim.fade_in);
                textSwitcher.setInAnimation(this,android.R.anim.fade_out);
            }
            textSwitcher.setText(texts[index]);
        }
        return true;
    }
}
