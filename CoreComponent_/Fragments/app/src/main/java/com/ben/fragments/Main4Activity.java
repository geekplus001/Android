package com.ben.fragments;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(this);
        String name = sp.getString("edittext_preference","");
        System.out.println(name);
    }
}
