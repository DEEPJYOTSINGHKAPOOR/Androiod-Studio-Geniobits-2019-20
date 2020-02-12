package com.example.loginproject.Models;

import android.content.Context;
import android.content.SharedPreferences;

public class User {

    public static final String SHARED_PREFS="user_details";

    public static final String EMAIL ="Email" ;

    public static final String PHONENUMBER="";

    public static final String USERNAME ="" ;

    public static final String SWITCH1="" ;

    public static final String TOKEN="";


    private String username;
    private String email;
    private String phoneNumber;
    private String token;
    private Boolean switchOnOff ;

    Context context ;

    SharedPreferences  sharedPreferences ;
    //constructor.
    public User(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE) ;
    }

    //getters and setters .

    public String getUsername() {
        username=sharedPreferences.getString(USERNAME,"");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        sharedPreferences.edit().putString(USERNAME,username).apply();
    }
    public String getEmail() {
        email=sharedPreferences.getString(EMAIL,"");
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString(EMAIL,email).apply();
    }
    public String getPhoneNumber() {
        phoneNumber=sharedPreferences.getString(PHONENUMBER,"");
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        sharedPreferences.edit().putString(PHONENUMBER,phoneNumber).apply();
    }
    public Boolean getSwitchOnOff() {
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
        return switchOnOff;
    }
    public void setSwitchOnOff(Boolean switchOnOff) {
        this.switchOnOff = switchOnOff;
        sharedPreferences.edit().putBoolean(SWITCH1,switchOnOff).apply();
    }
    public void removeUser(){
        sharedPreferences.edit().clear().apply();
    }
    public String getToken() {
        token=sharedPreferences.getString(TOKEN,""); // default token is empty
        return token;
    }
    public void setToken(String token) {
        this.token = token;
        sharedPreferences.edit().putString(TOKEN,"").apply();
    }
}