package com.example.loginproject.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class CalenderActivity extends AppCompatActivity {
    private static final String TAG = "CalenderActivity";
    //    private CalendarView calendarView ;
    private CompactCalendarView compactCalendarView;

    private TextView mStreakTextView;

    private TextView mCurrentMonth ;

    private TextView mAttendedSessionsThisMonth ;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());

    private BarChart barChart ;


    //vars
    private int myStreakCount = 0;

    private int sessionsAttendedThisMonth=0 ;
    int countJan =0;
    int countFeb =0;
    int countMar =0;
    int countApr =0;
    int countMay =0;
    int countJun =0;
    int countJul =0;
    int countAug =0;
    int countSep =0;
    int countOct =0;
    int countNov =0;
    int countDec =0;



    private ArrayList<String> presentDatesArray;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        compactCalendarView = findViewById(R.id.calender_CalenderViewId);

        compactCalendarView.setUseThreeLetterAbbreviation(true);
        presentDatesArray = new ArrayList<>();
        mStreakTextView = findViewById(R.id.calender_streakCountTextId);
        mCurrentMonth=findViewById(R.id.calender_currentMonthId) ;
        mAttendedSessionsThisMonth=findViewById(R.id.calender_AttendedSessionsThisMonthId);
        barChart=findViewById(R.id.calender_barGraphId);
         ArrayList<String>thisDates=new ArrayList<>();

        ArrayList<BarEntry> barEntries =new ArrayList<>() ;
        barEntries.add(new BarEntry(44f,0)) ;
        barEntries.add(new BarEntry(88f,1)) ;
        barEntries.add(new BarEntry(99f,2)) ;
        barEntries.add(new BarEntry(12f,3)) ;
        barEntries.add(new BarEntry(19f,4)) ;

        BarDataSet barDataSet =new BarDataSet(barEntries,"Dates");

        thisDates.add("Jan");
        thisDates.add("Feb");
        thisDates.add("Mar");
        thisDates.add("Apr");
        thisDates.add("May");
        thisDates.add("Jun");
        thisDates.add("Jul");
        thisDates.add("Aug");
        thisDates.add("Sep");
        thisDates.add("Oct");
        thisDates.add("Nov");
        thisDates.add("Dec");



        BarData theData =new BarData(thisDates,barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        //set an event for ""

        final ActionBar actionBar = getSupportActionBar();
        //assert actionBar != null;

        if (actionBar != null) {
           actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(null);
        }


        String str_date8 = "2019-12-13";
        String str_date7 = "2019-12-14";
        String str_date6 = "2019-12-15";
        String str_date5 = "2020-01-01";
        String str_date4 = "2020-01-02";
        String str_date3 = "2020-01-03";
        String str_date2 = "2020-01-04";
        String str_date1 = "2020-01-05";
        String str_date0 = "2020-01-06";


        presentDatesArray.add(str_date0);
        presentDatesArray.add(str_date1);
        presentDatesArray.add(str_date2);
        presentDatesArray.add(str_date3);
        presentDatesArray.add(str_date4);
        presentDatesArray.add(str_date5);
        presentDatesArray.add(str_date6);
        presentDatesArray.add(str_date7);
        presentDatesArray.add(str_date8);


        try {
            setAttendance(presentDatesArray);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mStreakTextView.setText("Your streak count is  : " + myStreakCount);

        setCurrentMonth();
        datesPresentInCurrentMonth(presentDatesArray) ;

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);

                String dateToString =firstDayOfNewMonth.toString();
                String[] dateStringArray = dateToString.split(" ");
                String currentYear=dateStringArray[5] ;
                String currentMonth=dateStringArray[1] ;

                mCurrentMonth.setText(currentMonth+"-"+currentYear);

                int countOfCurrentMonth= 0;

                switch(currentMonth){
                    case "Jan":
                        countOfCurrentMonth=countJan;
                        break ;
                    case "Feb":
                        countOfCurrentMonth=countFeb ;
                        break ;
                    case "Mar":
                        countOfCurrentMonth=countMar ;
                        break ;
                    case "Apr":
                        countOfCurrentMonth=countApr ;
                        break ;
                    case "May":
                        countOfCurrentMonth=countMay ;
                        break ;
                    case "Jun":
                        countOfCurrentMonth=countJun ;
                        break ;

                    case "Jul":
                        countOfCurrentMonth=countJul ;
                        break ;

                    case "Aug":
                        countOfCurrentMonth=countAug ;
                        break ;

                    case "Sep":
                        countOfCurrentMonth=countSep ;
                        break ;

                    case "Oct":
                        countOfCurrentMonth=countOct ;
                        break ;

                    case "Nov":
                        countOfCurrentMonth=countNov ;
                        break ;

                    case "Dec":
                        countOfCurrentMonth=countDec ;
                        break ;
                    default:
                        throw new IllegalStateException("Unexpected value: ");
                }


                presentY(countOfCurrentMonth);

            }
        });
    }

    private void datesPresentInCurrentMonth(ArrayList<String> presentDatesArray) {
        for(String x:presentDatesArray){
            String []splitDate=x.split("-");

            String myMonth=splitDate[1] ;
            Log.d(TAG, "datesPresentInCurrentMonth: "+ myMonth);
//            String myMonthInEnglish ;


            switch(myMonth){
                case "01":
                    countJan++ ;
                    break ;
                case "02":
                    countFeb++ ;
                    break ;

                case "03":
                    countMar++ ;
                    break ;
                case "04":
                    countApr++ ;
                    break ;
                case "05":
                    countMay++ ;
                    break ;

                case "06":
                    countJun++ ;
                    break ;

                case "07":
                    countJul++ ;
                    break ;

                case "08":
                    countAug++ ;
                    break ;

                case "09":
                    countSep++ ;
                    break ;

                case "10":
                    countOct++ ;
                    break ;

                case "11":
                    countNov++;
                    break ;

                case "12":
                    countDec++ ;
                    break ;
                default:
                    throw new IllegalStateException("Unexpected value: " + myMonth);
            }

        }
    }


    @SuppressLint("SetTextI18n")
    private void presentY(int countOfCurrentMonth) {
        mAttendedSessionsThisMonth.setText("Sessions Attended this month is -> "+countOfCurrentMonth);
    }



    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setCurrentMonth() {
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        Date d1 = null;
        try{
            d1 = new Date();

            String dateToString =d1.toString();
            String[] dateStringArray = dateToString.split(" ");
            String currentYear=dateStringArray[5] ;
            String currentMonth=dateStringArray[1] ;
            mCurrentMonth.setText(currentMonth+"-"+currentYear);

        }catch (Exception e){ e.printStackTrace(); }



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setAttendance(ArrayList<String> presentDatesArray) throws ParseException {

        for (String x : presentDatesArray) {
            @SuppressLint("SimpleDateFormat")
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = (Date) formatter.parse(x);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            @SuppressLint("SimpleDateFormat")
            String dateFormat=new SimpleDateFormat("EEE").format(date);
            if(dateFormat.equals("Sun")){
                Event attendanceEvent = new Event(Color.BLACK, date.getTime(), "Present");
                compactCalendarView.addEvent(attendanceEvent);
            }else {

                Event attendanceEvent = new Event(Color.GREEN, date.getTime(), "Present");
                compactCalendarView.addEvent(attendanceEvent);
            }
        }

        setStreak(presentDatesArray);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setStreak(ArrayList<String> presentDates) throws ParseException {

//        LocalDate todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(presentDates[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String todayDateString = presentDates.get(0);

        Toast.makeText(this, "" + todayDateString, Toast.LENGTH_SHORT).show();

        String dateInString ;


        //convert String to LocalDate
        LocalDate todayLocalDate = LocalDate.parse(todayDateString, formatter);
        boolean streakEnd = false;
        int countStreak = 0;
//            for (LocalDate date = todayLocalDate; ; date = date.plusDays(-1)) {
        for(LocalDate date=todayLocalDate ; ;date=date.plusDays(-1)){
            dateInString=date.toString();
            streakEnd=true;

            Log.d(TAG, "setStreak: "+" date in presentDates array is : "+ presentDates.get(0) +" date in dateInString is "+dateInString);

            for(String x: presentDates){
                if(dateInString.equals(x)){
                    countStreak++ ;
                    streakEnd=false;
                }
            }
            if(streakEnd){
                break ;
            }
        }
        myStreakCount=countStreak ;
    }
}