package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameET ;
    private EditText passwordET ;
    private Button loginBT ;
    private static final String TAG = "LoginActivity";
    private TextView registerTV ;

//    private final String nameCurrent="kapoordeepjyotsingh29@gmail.com" ;
//    private final String passwordCurrent="12345" ;

//    String login_url="http://192.168.137.1:8000/login_user/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate: create ho gaya mere bhai");

        userNameET=findViewById(R.id.username_loginID);
        passwordET=findViewById(R.id.password_loginID);
        loginBT=findViewById(R.id.button_loginID);
        registerTV=findViewById(R.id.register_loginID);


         final String username1=userNameET.getText().toString();
         final String password1=passwordET.getText().toString();
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: On click working sahi se.");
//                authenticateUser(userNameET.getText().toString(),passwordET.getText().toString());


             final StringRequest stringRequest =new StringRequest(Request.Method.POST, Helper.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {

                                    JSONObject jsonObject =new JSONObject(response);

//                                    User user=new User(getApplicationContext());
//                                    user.setToken(jsonObject.getString("token"));

                                    User user=new User(getApplicationContext());
                                    user.setToken(jsonObject.getString("token"));
                                    user.setEmail(jsonObject.getString("email"));
                                    user.setUsername(jsonObject.getString("username"));
                                    user.setPhoneNumber(jsonObject.getString("phone_no"));

                                    if(jsonObject.getString("error").equals("False")){

                                        Intent intent=new Intent(LoginActivity.this,ProfileUser.class);
                                     //   Intent intent =new Intent(LoginActivity.this,ProfileUser.class);
                                       // intent.putExtra("token",jsonObject.getString("token"));
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(LoginActivity.this, "In valid user name or password .", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(LoginActivity.this, "nooooo.", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                                Toast.makeText(LoginActivity.this,response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("username",username1);
                        params.put("password",password1);
                        return params;
                    }
                };
//                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
//                requestQueue.add(stringRequest);
                   MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
            }

        });

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpForm.class));
                finish();
            }
        });

    }


    private void authenticateUser(String username1,String password1){
        Log.d(TAG, "authenticateUser: authentication ke andar ho app");
        if(username1.isEmpty() || password1.isEmpty()){
            Toast.makeText(LoginActivity.this, "Username or Password Field can't be empty . ", Toast.LENGTH_SHORT).show();
            return ;
        }
        else{
            if(username1.length()<3 ){
                Toast.makeText(LoginActivity.this, "UserName is invalid.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(password1.length()<4){
                Toast.makeText(LoginActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                return ;
            }
        }
    }

}