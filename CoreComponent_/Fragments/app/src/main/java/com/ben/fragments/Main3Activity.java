package com.ben.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity implements MenuFragment.MyMenuListener{

    private MenuFragment menuFragment;
    private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        menuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.menuFragment);
        mainFragment = (MainFragment) getFragmentManager().findFragmentById(R.id.mainFragment);
    }

    @Override
    public void changeValue(String value) {
        mainFragment.changeTextViewValue(value);
    }
}
