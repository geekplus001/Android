package com.ben.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showWindow(View v)
    {
        View view = getLayoutInflater().inflate(R.layout.pop_window_layout,null);
        //创建popWindow（窗体的视图，宽，高）
        PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.btn_default));
        popupWindow.setAnimationStyle(android.R.style.Animation_Translucent);
        popupWindow.getBackground().setAlpha(100);//设置透明度
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.showAtLocation(v, Gravity.BOTTOM,0,0);


        //获取屏幕尺寸
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
    }
}

