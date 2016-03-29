package com.ben.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

public class SharePreferencesActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        editText = (EditText) findViewById(R.id.editText3);
        //获取当前程序的SharePreference对象
        sp = getSharedPreferences("msg", Context.MODE_APPEND);
    }

    //在该事件方法里存储数据
    @Override
    protected void onPause() {
        super.onPause();
        String msg = editText.getText().toString();
        if(TextUtils.isEmpty(msg))
        {
            return;
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("msg",msg);
        editor.commit();
    }

    //还原数据
    @Override
    protected void onResume() {
        super.onResume();
        editText.setText(sp.getString("msg", ""));
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("msg");
        editor.commit();
    }
}
