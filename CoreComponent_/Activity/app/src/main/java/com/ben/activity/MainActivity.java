package com.ben.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
Activity的三大状态和七大生命周期方法
 */
public class MainActivity extends Activity {

    /*
    Activity创建时第一个调用的方法，
    通常我们在该方法中加载布局文件，初始化UI组件，事件注册等
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    在onCreate方法之后调用，用于显示界面，但是用户还不能进行交互
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
    当一个stoped状态的Activity被返回时候调用，之后再调用onResume方法进入运行状态
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /*
    在onStart方法后调用，该方法执行完成后，用户可进行交互，当前Activity进入resumed状态（运行状态）
    当一个paused状态的Activity被重新返回时候，会再次调用该方法，让Activity进入运行状态
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /*
    当其他Activity（透明或者窗口模式）进入时候，该方法会被调用，让当前Activity进入paused状态（暂停状态）
    当前Activity可见但不可交互，如果其他更高优先级的app需要内存时时候，当前Activity可能会被销毁（killed）
    当Activity被返回时候会调用onResume方法
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /*
    当其他Activity完全覆盖改Activity时候，会被调用，当前Activity进入stoped状态（停止状态）
    不可见，如果其他更高优先级的app需要内存时候，当前Activity可能会被销毁（killed）
    当前Activity被返回时候会调用onRestart方法
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /*
    当前Activity被销毁时候调用，通常在该方法中用来释放资源，当前Activity  （killed）
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
