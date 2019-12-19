package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.loginproject.Models.User;
import com.example.loginproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                User user=new User(getApplicationContext()) ;
                if(user.getToken()!=null){
                    Intent intent =new Intent(MainActivity.this,ProfileUser.class) ;

                    intent.putExtra("username",user.getUsername());
                    intent.putExtra("phone_no",user.getPhoneNumber());
                    intent.putExtra("email",user.getEmail());

                    startActivity(intent);

//                    startActivity(new Intent(MainActivity.this,ProfileUser.class));
                    finish();
                }
                else{
                    startActivity(new Intent(MainActivity.this , LoginActivity.class));
                    finish();
                }
            }
        },1300);
    }
}
