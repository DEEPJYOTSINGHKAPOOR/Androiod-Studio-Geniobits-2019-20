package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.loginproject.Models.MySingleton;
import com.example.loginproject.Models.User;
import com.example.loginproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Hi extends AppCompatActivity {
    private Button button ;
    private TextView nameTV ;
    private TextView emailTV ;
    private TextView mobileTV ;
    private TextView sampleTextTV ;
    private Button logoutBT ;

    String json_url="http://192.168.43.107/example/getinfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);

        button=findViewById(R.id.getInfo_hiID);
        nameTV=findViewById(R.id.name_HiID);
        emailTV=findViewById(R.id.email_HiID);
        mobileTV=findViewById(R.id.mobile_HiID);
        sampleTextTV=findViewById(R.id.sampleText_hiID);
        logoutBT=findViewById(R.id.logout_hiID);


        logoutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new User(Hi.this).removeUser();
                Toast.makeText(Hi.this, "Logout successfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Hi.this,LoginActivity.class));
                finish();
            }
        });












        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, json_url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    nameTV.setText(response.getString("Name"));
                                    emailTV.setText(response.getString("Email"));
                                    mobileTV.setText(response.getString("Mobile"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Hi.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);


                String message= getIntent().getStringExtra("message");
                sampleTextTV.setText(message);
            }
        });

    }
}
