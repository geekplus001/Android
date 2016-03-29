package com.ben.timedate2;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textView_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView_date = (TextView) findViewById(R.id.textView_date);

    }
    /*
    设置日期的按钮事件
     */
    public void setDateClick(View v)
    {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getFragmentManager(),"datePicker");
    }

    public void setDateValue(int year,int month,int day)
    {
        textView_date.setText(year+"年"+(month+1)+"月"+day+"日");
    }
}
