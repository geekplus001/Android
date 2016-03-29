package com.ben.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TitleFragment titleFragment;
    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleFragment = (TitleFragment) getFragmentManager().findFragmentById(R.id.title_fragment);
        contentFragment = (ContentFragment) getFragmentManager().findFragmentById(R.id.content_fragment);

    }
}
