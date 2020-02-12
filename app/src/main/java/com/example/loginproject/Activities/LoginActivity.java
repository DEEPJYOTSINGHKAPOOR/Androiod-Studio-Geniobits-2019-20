package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText emailET ;
    private EditText passwordET ;
    private Button loginBT ;
    private static final String TAG = "LoginActivity";
    private TextView registerTV ;

    private TextView mForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate: create ho gaya mere bhai");

        emailET=findViewById(R.id.email_loginID);
        passwordET=findViewById(R.id.password_loginID);
        loginBT=findViewById(R.id.button_loginID);
        registerTV=findViewById(R.id.register_loginID);
        mForgotPassword=findViewById(R.id.forgotPassword_loginId);


        final String email1 ;
        final String phoneNumber1 ;

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email1=emailET.getText().toString();
                final String password1=passwordET.getText().toString();

                Log.d(TAG, "onClick: Login Button Clicked.");
                Log.d(TAG, "email is : "+email1);
                Log.d(TAG, "pass is : "+password1);


                final StringRequest stringRequest =new StringRequest(Request.Method.POST, com.example.loginproject.Utils.Urls.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject =new JSONObject(response);
                                    if(jsonObject.getString("error").equals("False")){
                                        Toast.makeText(LoginActivity.this, "in if and:"+jsonObject.getString("token"), Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(LoginActivity.this,ProfileUser.class);
                                        intent.putExtra("token",jsonObject.getString("token"));
                                        startActivity(intent);
                                    }else{
                                        Log.e(TAG, "onResponse: "+jsonObject.getString("message") );
                                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                        startActivity(getIntent());
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    Log.e(TAG, "onResponse: In catch "+e.getLocalizedMessage());
                                    Toast.makeText(LoginActivity.this, "nooooo.", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                                Toast.makeText(LoginActivity.this,response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "onErrorResponse: "+ error.getLocalizedMessage() );
                                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("email",email1);
                        params.put("password",password1);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(stringRequest);

            }
        });
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
    }


}