package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
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
import com.example.loginproject.R;
import com.example.loginproject.Utils.Urls;
import com.example.loginproject.Utils.UserShared;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VerifyEmailActivity extends AppCompatActivity {
    private static final String TAG = "VerifyEmailActivity";
    EditText mOtp ;
    Button mProceed ;
    String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        init();

        mProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedClicked();
            }
        });

    }
    private void init() {
        mOtp=findViewById(R.id.otp_VerifyEmailId);
        mProceed=findViewById(R.id.proceed_VerifyEmailId);
    }

    private void proceedClicked() {
                 otp=mOtp.getText().toString();
                if(otp.isEmpty() || otp.length()<6){
                    mOtp.setError("Please Enter a valid otp.");
                    mOtp.requestFocus();
                    return;
                }else{
                    final StringRequest stringRequest =new StringRequest(Request.Method.POST, Urls.verifyEmailUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONObject jsonObject= null;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        if(jsonObject.getString("error").equals("False")){
                                            Intent intent=new Intent(VerifyEmailActivity.this,ProfileUser.class);
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(VerifyEmailActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "onErrorResponse: ", error);
                                }
                            }

                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            SharedPreferences sharedPreferences=getBaseContext().getSharedPreferences(UserShared.sharedPrefernces,MODE_PRIVATE);
                            String token=sharedPreferences.getString(UserShared.userToken,"DEFAULT TOKEN");
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("otp",otp);
                            params.put("token",token);
                            return params;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(VerifyEmailActivity.this);
                    requestQueue.add(stringRequest);
                }

    }
}