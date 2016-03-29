package com.ben.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


/*
设置全屏：
1、通过代码
2、通过清单文件的主题 android:theme="@android:style/Theme.Black.NoTitleBar.Fullscreen">

设置窗体模式
1、在清单文件设置android:theme="@android:style/Theme.DeviceDefault.Dialog"
 */
public class ScreenOrientationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //通过代码设置屏幕方向
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_screen_orientation);
    }
}
