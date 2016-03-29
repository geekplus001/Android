package com.ben.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private String[] titles = {"第一页","第二页","第三页","第四页"};
    private ArrayList<View> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("111111111111111111111");
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        System.out.println("222222222222222222222");
        initViews();
        System.out.println("33333333333333333333");
        viewPager.setAdapter(new MyPagerAdapter());
        System.out.println("444444444444444444");

//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void initViews()
    {
        views.add(getLayoutInflater().inflate(R.layout.layout1,null));
        views.add(getLayoutInflater().inflate(R.layout.layout2,null));
        views.add(getLayoutInflater().inflate(R.layout.layout3, null));
        views.add(getLayoutInflater().inflate(R.layout.layout4, null));

        pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_bright));
        pagerTabStrip.setTextColor(Color.WHITE);
//
//        //设置默认显示的选项卡
//        viewPager.setCurrentItem(1);
//        //注册事件
//        viewPager.setOnPageChangeListener(this);

        System.out.println("?????????????");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this,"page--"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter{

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

        public CharSequence getPageTitle(int position)
        {
            return titles[position];
        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
