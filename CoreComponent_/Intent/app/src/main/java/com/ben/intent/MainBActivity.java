package com.ben.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
    }

    public void startA(View v)
    {
        Intent intent = new Intent(this,MainAActivity.class);
        startActivity(intent);
    }
    public void startB(View v)
    {
        Intent intent = new Intent(this,MainBActivity.class);
        startActivity(intent);
    }
}
