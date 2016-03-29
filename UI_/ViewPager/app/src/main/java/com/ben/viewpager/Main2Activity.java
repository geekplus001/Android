package com.ben.viewpager;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Main2Activity extends Activity implements OnPageChangeListener{

    private ViewPager viewPager;
    private ArrayList<View> views = new ArrayList<>();
    private ImageView[] imageViews;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        initPoint();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        views.add(getLayoutInflater().inflate(R.layout.layout1, null));
        views.add(getLayoutInflater().inflate(R.layout.layout2, null));
        views.add(getLayoutInflater().inflate(R.layout.layout3, null));
        views.add(getLayoutInflater().inflate(R.layout.layout4, null));

        viewPager.setOnPageChangeListener(this);

//        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        viewPager.setAdapter(new MyPagerAdapter());
    }

    private void initPoint()
    {
        LinearLayout point_layout = (LinearLayout) findViewById(R.id.point_layout);
        imageViews = new ImageView[views.size()];
        for(int i=0;i<imageViews.length;i++)
        {
            imageViews[i] = (ImageView) point_layout.getChildAt(i);
        }
        index = 0;
        imageViews[index].setImageResource(R.drawable.bullet_purple);

    }

    private void setCurrentPoint(int position)
    {
        if(index<0 || index>imageViews.length-1 || index==position)
        {
            return;
        }
        imageViews[index].setImageResource(R.drawable.bullet_white);
        imageViews[position].setImageResource(R.drawable.bullet_purple);
        index = position;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentPoint(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();

        }

        public Object instantiateItem(ViewGroup container,int position)
        {
            View v = views.get(position);
            container.addView(v);
            return v;
        }
        public void destroyItem(ViewGroup container,int position,Object object)
        {
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }



    }
}
