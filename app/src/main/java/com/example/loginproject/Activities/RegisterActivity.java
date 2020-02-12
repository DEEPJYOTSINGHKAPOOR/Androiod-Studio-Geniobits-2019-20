package com.example.loginproject.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.loginproject.R;
import com.example.loginproject.Utils.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBT ;
    private EditText passwordET ;
    private EditText reEnterPasswordET ;
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText emailET;
    private EditText phoneNumberET ;
    private static final String TAG = "RegisterActivity";
    private String firstname1,lastname1,password1,re_password1,phone_no1,email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBT=findViewById(R.id.register_signupID) ;
        passwordET=findViewById(R.id.password_RegisterActivityID);
        reEnterPasswordET=findViewById(R.id.reenterpassword_RegisterActivityID);
        firstNameET=findViewById(R.id.firstname_RegisterActivityID);
        lastNameET=findViewById(R.id.lastname_RegisterActivityID);
        emailET=findViewById(R.id.email_RegisterActivityID);
        phoneNumberET=findViewById(R.id.phoneNumber_RegisterActivityID);




        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname1=firstNameET.getText().toString().trim();
                lastname1=lastNameET.getText().toString().trim();
                password1=passwordET.getText().toString().trim();
                re_password1=reEnterPasswordET.getText().toString().trim();
                phone_no1=phoneNumberET.getText().toString().trim();
                email1=emailET.getText().toString().trim();
                StringRequest stringRequest =new StringRequest(Request.Method.POST, Urls.REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                                parseData(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }

                ){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params = new HashMap<>();

                        params.put("first_name",firstname1);
                        params.put("last_name",lastname1);
                        params.put("password",password1);
                        params.put("re_password",re_password1);
                        params.put("phone_no",phone_no1);
                        params.put("email",email1);
                        return params;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(stringRequest);
            }

        });
    }

    public void parseData(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.getString("error").equals("False")){
                Intent intent =new Intent(RegisterActivity.this, VerifyEmailActivity.class);
                Toast.makeText(RegisterActivity.this, "A otp has been sent to your email.", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            Toast.makeText(RegisterActivity.this, "Re-enter credentials.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Response Error " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}