package com.ben.timedate2;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView_time = (TextView) findViewById(R.id.textView_time);
    }

    public void setTimeValue(int hour,int minute)
    {
        textView_time.setText(hour + ":" + minute);
    }
    public void setTimeClick(View v)
    {
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getFragmentManager(), "timePicker");
    }




}
