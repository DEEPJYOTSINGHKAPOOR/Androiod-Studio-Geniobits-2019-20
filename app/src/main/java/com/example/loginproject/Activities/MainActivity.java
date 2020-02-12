package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.loginproject.Models.User;
import com.example.loginproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(new User(MainActivity.this).getToken().equals("") || new User(MainActivity.this).getToken()==null )
                {
                    startActivity(new Intent(MainActivity.this,JoinAGymActivity.class));
                    finish() ;
                }else{
                    startActivity(new Intent(MainActivity.this , JoinAGymActivity.class));
                    finish();
                }


            }
        },1300);
    }
}
