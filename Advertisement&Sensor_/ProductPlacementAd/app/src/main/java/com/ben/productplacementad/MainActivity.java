package com.ben.productplacementad;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.waps.AppConnect;
import cn.waps.AppListener;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppConnect.getInstance(this);//初始化

        //监听广告墙关闭事件
        AppConnect.getInstance(this).setOffersCloseListener(new AppListener() {
            @Override
            public void onOffersClose() {
                // TODO 关闭积分墙时癿操作代码
                super.onOffersClose();
            }
        });

        //显示互动广告
        LinearLayout adlayout =(LinearLayout)findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, adlayout);

        //预加载插屏广告数据
        AppConnect.getInstance(this).initPopAd(this);

        // 参数 true 表示可使用设备 back 键关闭揑屏广告，丌调用该代码则使用默认值 false 丌可关闭
        AppConnect.getInstance(this).setPopAdBack(true);


        //迷你广告
        //设置迷你广告背景颜色
        AppConnect.getInstance(this).setAdBackColor(Color.argb(50, 120, 240, 120));
        //设置迷你广告广告语颜色
        AppConnect.getInstance(this).setAdForeColor(Color.BLUE);
        //若未设置以上两个颜色，则默认为黑底白字
        LinearLayout miniLayout =(LinearLayout)findViewById(R.id.miniAdLinearLayout);
        AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10); //默认 10 秒切换一次广告
    }

    public void showClick(View view)
    {
        //积分墙
        AppConnect.getInstance(this).showAppOffers(this);
    }

    public void showClick2(View view)
    {
//        AppConnect.getInstance(this).showPopAd(this);//方式一插屏广告
        AppConnect.getInstance(this).showPopAd(this, new AppListener(){
            @Override
            public void onPopClose() {super.onPopClose(); }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppConnect.getInstance(this).close();//关闭广告平台
    }
}
