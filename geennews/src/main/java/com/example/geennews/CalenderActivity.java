package com.example.geennews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private MaterialCalendarView mCalendarView;
    private static final String TAG = "CalenderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        initView();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("选择日期");
        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        Calendar calendar= Calendar.getInstance();
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2013, 4, 3))
                .setMaximumDate(CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                String dateSS = df.format(date.getDate()).toString();

                Log.d(TAG, "onDateSelected: "+   df.format( date.getDate()).toString());

                Intent intent = new Intent();
                intent.setAction("com.geekdemo.date");

                intent.putExtra("date",dateSS);
                LocalBroadcastManager.getInstance(CalenderActivity.this).sendBroadcast(intent);
                finish();


            }
        });
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
    }
}
