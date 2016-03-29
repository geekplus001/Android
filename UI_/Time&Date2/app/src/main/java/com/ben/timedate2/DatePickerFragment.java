package com.ben.timedate2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/10/30 0030.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Main2Activity main2Activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2Activity = (Main2Activity) getActivity();
    }

    //用于创建日期对话框的事件方法
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c  = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,year,month,day);

        return dialog;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        main2Activity.setDateValue(year,monthOfYear,dayOfMonth);
    }
}
