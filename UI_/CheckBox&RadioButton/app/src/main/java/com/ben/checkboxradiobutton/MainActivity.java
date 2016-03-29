package com.ben.checkboxradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private CheckBox cb_xs,cb_games,cb_movies;
    private RadioButton rb_male,rb_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_xs = (CheckBox)findViewById(R.id.checkBox_xs);
        cb_games = (CheckBox)findViewById(R.id.checkBox2_games);
        cb_movies = (CheckBox)findViewById(R.id.checkBox3_movies);

        rb_male = (RadioButton)findViewById(R.id.radioButton_male);
        rb_female = (RadioButton)findViewById(R.id.radioButton2_female);
        //注册事件
        cb_xs.setOnCheckedChangeListener(this);
        cb_games.setOnCheckedChangeListener(this);
        cb_movies.setOnCheckedChangeListener(this);

        rb_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this,"isChecked="+isChecked,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId())
        {
            case R.id.checkBox_xs:
                if(isChecked) Toast.makeText(this,"你选中了小说选项",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"你取消了了小说选项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox2_games:
                if(isChecked) Toast.makeText(this,"你选中了游戏选项",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"你取消了了游戏选项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox3_movies:
                if(isChecked) Toast.makeText(this,"你选中了电影选项",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"你取消了电影选项",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
