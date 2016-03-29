package com.ben.property_animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void click(View view)
    {
//        ObjectAnimator.ofFloat(view,"rotationX",0.0f,360.0f).setDuration(500).start();

        //组合多个动画
//        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("alpha",1f,0f,1f);
//        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleX",1f,0f,1f);
//        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("scaleY",1f,0f,1f);
//        ObjectAnimator.ofPropertyValuesHolder(view,p1,p2,p3).setDuration(500).start();


        final View v = view;
        //监听动画事件
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"alpha",1f,0f).setDuration(1000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup viewGroup = (ViewGroup) v.getParent();
                if(viewGroup!=null)
                {
                    viewGroup.removeView(v);
                    System.out.println("removed");
                }
            }

            //取消动画
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            //重复播放时候调用的方法
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        //省略写法
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//            }
//        });
        objectAnimator.start();
    }

    public void click2(View view)
    {
//        final View v = view;
//
//        //获取屏幕高度
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        //定义一个动画
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(v.getY(), displayMetrics.heightPixels,view.getY()).setDuration(500);
//       //监听动画的每个动作
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                v.setTranslationY((Float) animation.getAnimatedValue());
//            }
//        });
//        valueAnimator.start();


//        //AnimatorSet的使用
//        ObjectAnimator o1 = ObjectAnimator.ofFloat(view,"translationX",0f,200f);
//        ObjectAnimator o2 = ObjectAnimator.ofFloat(view,"translationY",0f,200f);
//        ObjectAnimator o3 = ObjectAnimator.ofFloat(view,"rotation",0f,360f);
//        AnimatorSet set =  new AnimatorSet();
//        set.setDuration(1000);
//
//        //插值器
//        set.setInterpolator(new BounceInterpolator());//反弹效果
////        set.setInterpolator(new AccelerateDecelerateInterpolator());//**效果
////        set.playTogether(o1,o2,o3);//三个动画同时执行
////        set.playSequentially(o1,o2,o3);//按序列执行
////        set.setStartDelay(300);//延时执行
//        //o1,o2同时执行，之后执行o3
//        set.play(o1).with(o2);
//        set.play(o3).after(o2);
//        set.start();
//
        //使用XML定义动画
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.test);
        animator.setTarget(view);
        animator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
