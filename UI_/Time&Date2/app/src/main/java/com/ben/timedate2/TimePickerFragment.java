package com.ben.timedate2;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/10/30 0030.
 */
public class TimePickerFragment  extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    int hour;
    int minute;
    private MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过getActiv方法获取该Fragment所依赖的Activity对象
        mainActivity = (MainActivity) getActivity();
    }

    //创建对话框的事件方法：该方法会在MainActivity的按钮事件中调用show方法时，会检查是否已存在Dialog，
    //当不存在时候会触发该事件，否则直接显示
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //获取当前系统时间
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        //创建时间对话框，参数为（上下文，设置时间事件，小时，分钟，是否为24小时制）
        TimePickerDialog dialog = new TimePickerDialog(getActivity(),this,hour,minute,true);
        return dialog;
    }

    //时间对话框中的“完成”按钮的单击事件
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
        //此处把用户设置的时间设置到界面的TextView组件上
        mainActivity.setTimeValue(hour,minute);
    }
}
