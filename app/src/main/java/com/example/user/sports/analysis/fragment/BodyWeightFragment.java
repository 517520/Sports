package com.example.user.sports.analysis.fragment;

import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sports.R;
import com.example.user.sports.analysis.fragment.ValueFormatter.MyMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.util.ArrayList;

/**
 * Created by user on 9/26/17.
 */

public class BodyWeightFragment extends Fragment {
    private View view;
    private LineChart mLineChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_analysis_body_weight, container, false);
        initLineChart();
        return view;
    }

    private void initLineChart() {
        mLineChart = (LineChart)view.findViewById(R.id.lineChart_Body_Weight);

        mLineChart.getDescription().setEnabled(false);   //设置描述文本
        mLineChart.setTouchEnabled(true);                //支持触控手势
        mLineChart.setDragEnabled(false);                 //设置缩放
        mLineChart.setScaleEnabled(false);                 //设置推动


        setData(10, 100);



        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextSize(10f);
        xAxis.setAxisLineWidth(2f);        //x轴轴线宽度
        xAxis.setTextColor(0x54FFFFFF);
        xAxis.enableGridDashedLine(10f,10f,0f);
        xAxis.setDrawGridLines(false);     //是否显示网格线
        xAxis.setDrawAxisLine(true);       //是否显示轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //x轴轴线位置
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                int day = (int) v+20;
                String date = "9月"+day+"号";
                return date;
            }
        });

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setLabelCount(3);
        leftAxis.setTextColor(0x54FFFFFF);
        leftAxis.setTextSize(14f);
        leftAxis.setDrawAxisLine(false);
        mLineChart.getAxisRight().setEnabled(false);      //限制Y轴右边
        leftAxis.enableGridDashedLine(10f,10f,0f);
        leftAxis.setDrawZeroLine(false);

        leftAxis.setAxisMaximum(120f);
        leftAxis.setAxisMinimum(10f);

        Legend legend = mLineChart.getLegend();
        legend.setEnabled(false);

        MarkerView markerView = new MyMarkerView(getContext(),R.layout.content_marker_view);
        mLineChart.setMarker(markerView);

        mLineChart.animateY(1500);



    }

    private void setData(int count, float range) {
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 10;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            // set the line to be drawn like this "- - - - - -"
            set1.setDrawHighlightIndicators(false);

            set1.setColor(0xFFFB6151);
            set1.setCircleColor(0xFFFB6151);
            set1.setLineWidth(1f);
            set1.setCircleRadius(2f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 10f}, 0f));
            set1.setFormSize(15.f);

            set1.setDrawValues(false);      //不显示顶点值
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);


            set1.setFillColor(0x406C6C6C);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mLineChart.setData(data);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
