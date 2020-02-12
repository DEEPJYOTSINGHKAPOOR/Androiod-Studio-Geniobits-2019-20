package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginproject.R;
import com.example.loginproject.Utils.UserShared;

import java.util.Calendar;

public class JoinAGymActivity extends AppCompatActivity {
    private static final String TAG = "JoinAGymActivity";
    private EditText mFullName;
    private TextView mDob;
    private Button mProceed;
    private DatePickerDialog.OnDateSetListener mDateSetListener ;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_agym);
        init();
        mDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String dateOfBirth = dayOfMonth + "/" + month + "/" + year;
                Log.d(TAG, "onDateSet: date " + dateOfBirth);
                Toast.makeText(JoinAGymActivity.this, "DOB IS : " + dateOfBirth, Toast.LENGTH_SHORT).show();
                mDob.setText(dateOfBirth);

                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int age = currentYear - year;

                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(UserShared.sharedPrefernces, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(UserShared.userAge, String.valueOf(age));
                editor.apply();
                Toast.makeText(JoinAGymActivity.this, "Your Age is : " + age, Toast.LENGTH_SHORT).show();
            }
        };

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    private void buttonClicked() {
        Calendar calendar= Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(
                JoinAGymActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,year,month,day
        );
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void init() {
        mFullName=findViewById(R.id.fullName_JoinAGymId);
        mDob=findViewById(R.id.DOB_JoinAGymId);
        mProceed=findViewById(R.id.proceed_JoinAGymId);
    }


}
