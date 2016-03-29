package com.ben.viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

    }
    float startX,endX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN)
        {
            startX = event.getX();
        }else if(action == MotionEvent.ACTION_UP)
        {
            if(startX>endX)
            {
                viewFlipper.setInAnimation(this,R.anim.in_rightlet);
                viewFlipper.setInAnimation(this,R.anim.out_rightleft);
                viewFlipper.showNext();
            }else if(endX>startX)
            {
                viewFlipper.setInAnimation(this,R.anim.in_leftright);
                viewFlipper.setInAnimation(this,R.anim.out_leftright);
                viewFlipper.showPrevious();
            }
        }
        return super.onTouchEvent(event);
    }
}
