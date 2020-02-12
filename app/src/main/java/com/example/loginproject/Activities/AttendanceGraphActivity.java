package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loginproject.R;
import com.github.mikephil.charting.charts.BarChart;

public class AttendanceGraphActivity extends AppCompatActivity {

    private BarChart barChart ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_graph);
    }
}
