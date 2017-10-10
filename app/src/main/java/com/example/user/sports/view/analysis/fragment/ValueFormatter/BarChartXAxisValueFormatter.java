package com.example.user.sports.view.analysis.fragment.ValueFormatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by user on 9/27/17.
 */
public class BarChartXAxisValueFormatter implements IAxisValueFormatter {
    String[] week = new String[]{"周一","周二","周三","周四","周五","周六","周日"};

    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        return week[(int)v];

    }
}
