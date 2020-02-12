package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginproject.Models.User;
import com.example.loginproject.R;
import com.example.loginproject.Utils.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Otp extends AppCompatActivity {
    private  final String TAG="OTP" ;
    private EditText otpET;


    private Button verifyBT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        final String token1=getIntent().getStringExtra("token") ;
        Toast.makeText(this,token1, Toast.LENGTH_SHORT).show();
        otpET=findViewById(R.id.otpenter_otpET);
        verifyBT=findViewById(R.id.verify_otpBT);

        final String email_from_previous_activity= getIntent().getStringExtra("email");

        final String phone_no_from_previous_activity=getIntent().getStringExtra("phone_no");

        final String username_from_previous_activity=getIntent().getStringExtra("user_name");

        verifyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String otp1 = otpET.getText().toString() ;
                Log.d(TAG, "onClick: Successful");
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.OTP_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    //otp verified too will recive "error,message,token"

                                    Log.d(TAG, "onResponse: "+response);
                                    JSONObject jsonObject3=new JSONObject(response);
                                     if(jsonObject3.getString("error").equals("False")){
                                         Log.d(TAG, "onResponse: OTP  "+jsonObject3.getString("token"));
                                         Intent intent =new Intent(Otp.this,ProfileUser.class);

                                         //yaha pe tum username,email and phone - number shared prefernce mee store karro
                                         // and use intent to go LOGIN ACTIVITY and in Extra pass TOKEN.

                                         User user =new User(Otp.this);
                                         user.setEmail(email_from_previous_activity);
                                         user.setUsername(username_from_previous_activity);
                                         user.setPhoneNumber(phone_no_from_previous_activity);

                                         // token send to LoginActivity .


                                         intent.putExtra("token",jsonObject3.getString("token"));
                                         startActivity(intent);





                                     }else{
                                         Log.e(TAG, "onResponse: "+ " error : True. " );
                                         Toast.makeText(Otp.this, "Something...wrong", Toast.LENGTH_SHORT).show();
                                     }


                                } catch (JSONException e) {
                                    Log.e(TAG, "onResponse: CATCH OF JSON EXCEPTION"+e.getLocalizedMessage());
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "onErrorResponse: "+error.getLocalizedMessage() );
                                Toast.makeText(Otp.this, "Error response of OTP", Toast.LENGTH_SHORT).show();
                                    error.printStackTrace();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params= new HashMap<String, String>();
                        params.put("otp",otp1) ;
                        params.put("token",token1);
                        return params ;
                    }
                };

//                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                RequestQueue requestQueue= Volley.newRequestQueue(Otp.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}