package com.ben.trackline_;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/13 0013.
 * 日期转换
 */
public class DataUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String toDate(Date date)
    {
        return sdf.format(date);
    }
}
