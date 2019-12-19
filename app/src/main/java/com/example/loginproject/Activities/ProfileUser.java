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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginproject.Models.MySingleton;
import com.example.loginproject.Models.User;
import com.example.loginproject.R;
import com.example.loginproject.Utils.Helper;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ProfileUser extends AppCompatActivity {

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


        final String username3=getIntent().getStringExtra("username");

        final String email3=getIntent().getStringExtra("email");

        final String phoneNumber3=getIntent().getStringExtra("phone_no");


        userNameTV=findViewById(R.id.username_profileID);
        emailTV=findViewById(R.id.email_profileID);
        phoneNumberTV=findViewById(R.id.phoneNumber_profileID);
        logoutBT=findViewById(R.id.logout_profileID);

        logoutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileUser.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                new User(ProfileUser.this).removeUser();
                startActivity(new Intent(ProfileUser.this, LoginActivity.class));
                finish();
            }
        });


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Helper.PROFILE_URL,
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
                           }
                        } catch (JSONException e) {
                            Toast.makeText(ProfileUser.this, "json catch occured", Toast.LENGTH_SHORT).show();
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

                RequestQueue requestQueue = Volley.newRequestQueue(ProfileUser.this);
                requestQueue.add(stringRequest);
//        MySingleton.getInstance(ProfileUser.this).addToRequestQueue(stringRequest);


//        userNameTV.setText(username3);

//        emailTV.setText(email3);

//        phoneNumberTV.setText(phoneNumber3);
    }

}
