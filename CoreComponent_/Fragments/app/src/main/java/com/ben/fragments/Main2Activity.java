package com.ben.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addContentLayout();
    }

    /*
    通过代码添加fragment
     */
    private void addContentLayout()
    {
        FragmentManager fragmentManager = getFragmentManager();
        //开启一个事务
        FragmentTransaction ft = fragmentManager.beginTransaction();

        contentFragment = new ContentFragment();
        ft.add(R.id.ff,contentFragment);
//        ft.remove()
//        ft.replace()
        ft.commit(); //提交事务
    }
}
