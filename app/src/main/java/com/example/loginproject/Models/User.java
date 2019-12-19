package com.example.loginproject.Models;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    public String getToken() {
        token = sharedPreferences.getString("token","");
        return token;
    }

    public void setToken(String token) {
        this.token = token;
//        System.out.println(token+"is set");
        sharedPreferences.edit().putString("token",this.token).apply();
    }

    public User(Context context){
        this.context=context ;
        sharedPreferences=context.getSharedPreferences("login_details",context.MODE_PRIVATE);
    }
    private Context context ;
    private String username;
    private String password ;
    private String token ;
    private String phoneNumber ;
    private String email ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("email",this.email).apply();
    }

    private String error ;
    SharedPreferences sharedPreferences ;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        sharedPreferences.edit().putString("phone_no",this.phoneNumber).apply();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        sharedPreferences.edit().putString("error",this.error).apply();
    }

    public String getUsername() {
        username = sharedPreferences.getString("username","");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        sharedPreferences.edit().putString("username",this.username).apply();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }


}
