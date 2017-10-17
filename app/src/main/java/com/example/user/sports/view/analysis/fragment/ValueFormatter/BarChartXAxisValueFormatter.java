package com.example.user.sports.view.analysis.fragment.ValueFormatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 9/27/17.
 */
public class BarChartXAxisValueFormatter implements IAxisValueFormatter {
    String[] week = new String[]{"周日","周一","周二","周三","周四","周五","周六"};

    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        int a= 5-getWeekOfDate();
        int k = (int)v+a;
        if (k>6)
            k=k%7;
        return week[k];
    }

    public int getWeekOfDate(){
        Date date = new Date();
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(date);
        int number = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return number;
    }
}
