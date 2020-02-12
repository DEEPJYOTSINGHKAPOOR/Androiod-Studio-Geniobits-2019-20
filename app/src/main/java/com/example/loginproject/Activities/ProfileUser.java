package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.example.loginproject.Models.MySingleton;
import com.example.loginproject.R;
import com.example.loginproject.Utils.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileUser extends AppCompatActivity {
    
    private static String TAG="ProfileUser";
    private TextView userNameTV ;
    private TextView emailTV;
    private TextView phoneNumberTV ;

    private Button logoutBT ;


   private  String username1 ;
    private String email1;
    private String phoneNumber1 ;
    private String error1 ;
    private String message1 ;
    private String token2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        
        final String token1 = getIntent().getStringExtra("token");



        userNameTV=findViewById(R.id.username_profileID);
        emailTV=findViewById(R.id.email_profileID);
        phoneNumberTV=findViewById(R.id.phoneNumber_profileID);
        logoutBT=findViewById(R.id.logout_profileID);

        logoutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest =new StringRequest(Request.Method.POST, Urls.LOGOUT_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(ProfileUser.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject jsonObject=new JSONObject(response);

                                    if(jsonObject.getString("error").equals("False")){
                                        Toast.makeText(ProfileUser.this, "If meee hu.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ProfileUser.this,LoginActivity.class));
                                    }else{
                                        Log.e(TAG, "onResponse:LOGOUT BUTTON "+" else of logout");
                                        startActivity(new Intent(ProfileUser.this,LoginActivity.class));
                                    }

                                } catch (JSONException e) {
                                    Log.e(TAG, "onResponse: LOGOUT BUTTON "+e.getLocalizedMessage());
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "onErrorResponse: "+error.getLocalizedMessage());
                                Toast.makeText(ProfileUser.this, "Error of Profile User.", Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params =new HashMap<String, String>();
                        params.put("token",token1);
                        return params;
                    }
                };
            }
        });


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.PROFILE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            username1="UserName : "+jsonObject.getString("username");
                            email1="Email : "+jsonObject.getString("email") ;
                            phoneNumber1="Phone Number : "+jsonObject.getString("phone_no");
                            error1=jsonObject.getString("error");
                            message1=jsonObject.getString("message");
                            token2=jsonObject.getString("token");

                           if(error1.equals("False")){
                               userNameTV.setText(username1);
                                emailTV.setText(email1);
                                phoneNumberTV.setText(phoneNumber1);
                           }else{
                               Toast.makeText(ProfileUser.this, "Else mee hu ", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(ProfileUser.this,LoginActivity.class);
                               startActivity(intent);
                           }
                        } catch (JSONException e) {
                            //Toast.makeText(ProfileUser.this, "json catch occured", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onResponse: JSONEXCEPTION  " + e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileUser.this, "Error listener of profile user", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String>params =new HashMap<String,String>();
                params.put("token",token1);
                return params;
            }
        };
//        MySingleton.getInstance(ProfileUser.this).addToRequestQueue(stringRequest);
    }
}