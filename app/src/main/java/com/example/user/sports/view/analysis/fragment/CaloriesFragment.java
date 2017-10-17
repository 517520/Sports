package com.example.user.sports.view.analysis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;
import com.example.user.sports.view.analysis.fragment.ValueFormatter.BarChartXAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * Created by user on 9/26/17.
 */
public class CaloriesFragment extends Fragment {
    private View view;
    private BarChart mChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_analysis_calories, container, false);
        initBarChart();
        return view;
    }

    private void initBarChart() {
        mChart = (BarChart)view.findViewById(R.id.barChart_calories);
        mChart.setNoDataText("最近无运动记录,请适当运动,保证身体健康");          //没有数据时

        mChart.setTouchEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextColor(0x54FFFFFF);    //x轴标签颜色
        xAxis.setTextSize(10f);            //x轴标签文字大小
        xAxis.setDrawGridLines(false);     //是否显示网格线
        xAxis.setDrawAxisLine(true);       //是否显示轴线
        xAxis.setLabelCount(7);           //x轴个数
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //x轴轴线位置
        xAxis.setAxisLineWidth(2f);        //x轴轴线宽度
        xAxis.setValueFormatter(new BarChartXAxisValueFormatter());

        YAxis leftAxis = mChart.getAxisLeft();
        YAxis rightAxis = mChart.getAxisRight();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setSpaceTop(20f);
        leftAxis.setSpaceBottom(10f);

        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);

        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        mChart.getDescription().setEnabled(false);

        Legend legend = mChart.getLegend();
        legend.setEnabled(false);


        mChart.setScaleYEnabled(false);
        mChart.setScaleXEnabled(false);

        setData(6,50);
        mChart.animateY(1500);






        //2 刷新
        //高亮
        //触摸回调
        //指定在哪个点做中心

    }
    private void setData(int count, float range) {

        float start = 0f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(0,1245));
        yVals1.add(new BarEntry(1,1499));
        yVals1.add(new BarEntry(2,1289));
        yVals1.add(new BarEntry(3,1703));
        yVals1.add(new BarEntry(4,1892));
        yVals1.add(new BarEntry(5,1602));
        yVals1.add(new BarEntry(6,2021));


        BarDataSet set1;


        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextColor(0xAFFFA322);
            data.setValueTextSize(10f);
            data.setBarWidth(0.4f);
            mChart.setData(data);
        }
        set1.setColor(0xFFFB6151);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
