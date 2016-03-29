package com.ben.appcontentshare;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.content);
        handlerContent();
    }

    private void handlerContent() {
        Intent intent = getIntent();
        String type = intent.getType();
        String action = intent.getAction();

        if(Intent.ACTION_SEND.equals(action))
        {
            if("text/plain".equals(type))
            {
                String info = intent.getStringExtra(Intent.EXTRA_TEXT);
                textView.setText(info);
            }else if(type.startsWith("image/"))
            {

            }
        }else if(Intent.ACTION_SEND_MULTIPLE.equals(action)&&type!=null)
        {

        }
    }
}
