package com.example.user.sports.view.mine.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.sports.R;
import com.example.user.sports.view.mine.adapter.HistoryAdapter;
import com.example.user.sports.view.mine.model.Exercise;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.listener.OnCalendarChangedListener;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private NCalendar ncalendar;
    private RecyclerView recyclerView;
    private TextView tv_month;
    private TextView tv_date;
    private RelativeLayout back_header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        ncalendar = (NCalendar) findViewById(R.id.ncalendarrrr);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_date = (TextView) findViewById(R.id.tv_date);
        back_header = (RelativeLayout) findViewById(R.id.back_headview_relativelayout);
        back_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        final List<Exercise> list = new ArrayList<>();
//        Exercise exercise1 = new Exercise(1,(float) 1.8,12);
//        Exercise exercise2 = new Exercise(2,(float) 5.2,24);
//        Exercise exercise3 = new Exercise(3,(float) 1.5,24);
//        list.add(0,exercise1);
//        list.add(1,exercise2);
//        list.add(2,exercise3);
//        updateUI(list);




        ncalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(DateTime dateTime) {
                tv_month.setText(dateTime.getMonthOfYear() + "月");
                tv_date.setText(dateTime.getYear() + "年" + dateTime.getMonthOfYear() + "月" + dateTime.getDayOfMonth() + "日");
                if (dateTime.getDayOfMonth()==17){
                    final List<Exercise> list = new ArrayList<>();
                    Exercise exercise1 = new Exercise(1,(float) 1.8,12);
                    Exercise exercise2 = new Exercise(2,(float) 5.2,24);
                    Exercise exercise3 = new Exercise(3,(float) 1.5,24);
                    list.add(0,exercise1);
                    list.add(1,exercise2);
                    list.add(2,exercise3);
                    updateUI(list);
                }
                else {
                    final List<Exercise> list2 = new ArrayList<>();
                    Exercise exercise1 = new Exercise(3, (float) 1.2, 13);
                    Exercise exercise2 = new Exercise(2, (float) 5.8, 30);
                    Exercise exercise3 = new Exercise(3, (float) 1.5, 17);
                    Exercise exercise4 = new Exercise(1, (float) 2.1, 9);
                    list2.add(0, exercise1);
                    list2.add(1, exercise2);
                    list2.add(2, exercise3);
                    list2.add(3, exercise4);
                    updateUI(list2);
                }

            }
        });


    }

    public void updateUI(List<Exercise> list){
        HistoryAdapter adapter = new HistoryAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
