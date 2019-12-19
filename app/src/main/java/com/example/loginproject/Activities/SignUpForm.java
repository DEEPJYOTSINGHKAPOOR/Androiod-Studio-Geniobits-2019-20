package com.example.loginproject.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.DialogInterface;
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
import com.example.loginproject.Models.MySingleton;
import com.example.loginproject.Models.User;
import com.example.loginproject.R;
import com.example.loginproject.Utils.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class SignUpForm extends AppCompatActivity {
    private Button registerBT ;
    private EditText passwordET ;
    private EditText reEnterPasswordET ;
    private EditText usernameET ;
    private EditText emailET;
    private EditText phoneNumberET ;
    private static final String TAG = "SignUpForm";
    AlertDialog.Builder builder ;

//    private static final String TAG = "SignUpForm";
//    private String server_url="http://192.168.31.32:8000/register_user/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        registerBT=findViewById(R.id.register_signupID) ;

        passwordET=findViewById(R.id.password_signUpFormID);

        reEnterPasswordET=findViewById(R.id.reenterpassword_signUpFormID);

        usernameET=findViewById(R.id.username_signUpFormID);

        emailET=findViewById(R.id.email_signUpFormID);

        phoneNumberET=findViewById(R.id.phoneNumber_signUpFormID);

        builder=new AlertDialog.Builder(SignUpForm.this);

        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username1,password1,re_password1,phone_no1,email1;
                username1=usernameET.getText().toString();
                password1=passwordET.getText().toString();
                re_password1=reEnterPasswordET.getText().toString();
                phone_no1=phoneNumberET.getText().toString();
                email1=emailET.getText().toString();


                Log.d(TAG, "onClick: click horra hai button");

                checkUser(password1,re_password1);

                StringRequest stringRequest =new StringRequest(Request.Method.POST, Helper.REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse: I am in response listener. ");
                                Toast.makeText(SignUpForm.this, "On response Successful", Toast.LENGTH_SHORT).show();
//                                builder.setTitle("Server Response");
//                                builder.setMessage("Response"+response);


                            /*
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        usernameET.setText("");
                                        passwordET.setText("");
                                        reEnterPasswordET.setText("");
                                        phoneNumberET.setText("");
                                        emailET.setText("");
                                    }
                                });
                                */
                                try {

                                    JSONObject jsonObject=new JSONObject(response);
                                    if(jsonObject.getString("error").equals("False")){
                                        Intent intent =new Intent(SignUpForm.this,LoginActivity.class);
//                                        intent.putExtra("message",jsonObject.getString("message"));
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(SignUpForm.this, "Re-enter credentials.", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(getIntent());
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(SignUpForm.this, "Re-enter credentials.", Toast.LENGTH_SHORT).show();
                                    finish();
                                    e.printStackTrace();
                                }
                                AlertDialog alertDialog=builder.create();
                                alertDialog.show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse: I am in Error Response");
                                Toast.makeText(SignUpForm.this, "Something Went Wrong Chotte", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }

                ){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();

                        params.put("username",username1);
                        params.put("password",password1);
                        params.put("re_password",re_password1);
                        params.put("phone_no",phone_no1);
                        params.put("email",email1);
                        return params;
                    }
                };
//                RequestQueue requestQueue= Volley.newRequestQueue(SignUpForm.this);
//                requestQueue.add(stringRequest);
                MySingleton.getInstance(SignUpForm.this).addToRequestQueue(stringRequest);

//                Intent intent =new Intent(SignUpForm.this ,LoginActivity.class);
//                startActivity(intent);
            }

        });
    }


private void checkUser(String pass ,String repass){
    if(pass.isEmpty() ){
        Log.d(TAG, "checkUser: checkUser working fine");
        Toast.makeText(SignUpForm.this, "Password Field can't be empty.", Toast.LENGTH_SHORT).show();
    }else if(pass.length()<4){
        Toast.makeText(SignUpForm.this, "Password too short.", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }
    else{
        if(pass.equals(repass)){
            Toast.makeText(SignUpForm.this, "Sign Up Successful.", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(SignUpForm.this,LoginActivity.class));
//            finish();
            return ;
        }
    }
}

}