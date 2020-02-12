package com.example.loginproject.Utils;

import com.android.volley.toolbox.StringRequest;

public class Urls {
    public static String MAIN_URL="http://192.168.43.215:8000/" ;
    public static String LOGIN_URL=MAIN_URL+"user_login/";
    public static String REGISTER_URL=MAIN_URL+"user_register/";
    public  static String PROFILE_URL=MAIN_URL+"user_profile/";
    public static String OTP_URL=MAIN_URL+"login_user_by_otp/";
    public static String LOGOUT_URL=MAIN_URL+"logout_user/";
    public static String verifyEmailUrl=MAIN_URL+"verify_email/";
}