package com.ben.fragments;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class PopBackTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back_task);
    }
    public void oneClick(View v)
    {
        PopBackFragment p1 = PopBackFragment.getInstance("one");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, p1);
        //把当前Fragment添加到Activity栈
        ft.addToBackStack(null);
        ft.commit();
    }
    public void twoClick(View v)
    {
        PopBackFragment p1 = PopBackFragment.getInstance("two");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content,p1);
        //把当前Fragment添加到Activity栈
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(getFragmentManager().getBackStackEntryCount()==0)
            {
                finish();
            }
            else
            {
                getFragmentManager().popBackStack();//出栈
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
