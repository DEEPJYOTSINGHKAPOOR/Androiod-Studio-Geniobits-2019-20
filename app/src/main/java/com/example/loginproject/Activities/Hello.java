package com.example.loginproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginproject.Models.MySingleton;
import com.example.loginproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Hello extends AppCompatActivity {

    private static final String TAG = "Hello";
    private TextView helloTV ;
    private Button getDataBT ;

    private String server_url="http://192.168.43.107/example/greetings.php";
    private String image_url="http://192.168.43.107/example/gb.png";



    ImageView myImageIV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Log.d(TAG, "onCreate: Create successfully.");

        helloTV=findViewById(R.id.sayhelloID);
        getDataBT=(Button) findViewById(R.id.getDataID);

        myImageIV=findViewById(R.id.imageHelloID);

        getDataBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: successful");

                final RequestQueue requestQueue = Volley.newRequestQueue(Hello.this) ;


                // If Json Array use karna hai too JsonArrayRequest karo.

                //Json object use kar rahe hai

//                JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET,
//                        "https://jsonplaceholder.typicode.com/todos/1", null,
//
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    Log.d(TAG, "onResponse: "+ response.getString("title"));
//                                    helloTV.setText(response.getString("title"));
//                                } catch (JSONException e) {
//                                    Log.d(TAG, "onResponse: Theres some exception.");
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.d(TAG, "onErrorResponse: Something went wrong.");
//                                Toast.makeText(Hello.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                );
//
//                requestQueue.add(jsonObjectRequest);

                StringRequest stringRequest =new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse: Successful.");
                                helloTV.setText(response);
//                                requestQueue.stop();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse: Something went wrong.");
                                error.printStackTrace();
                                Toast.makeText(Hello.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
//                                requestQueue.stop();
                            }
                        });
//
//                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        5000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

//                requestQueue.add(stringRequest);

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


                ImageRequest imageRequest=new ImageRequest(image_url,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                myImageIV.setImageBitmap(response);
                            }
                        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Hello.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }

                );

                MySingleton.getInstance(Hello.this).addToRequestQueue(imageRequest);


                startActivity(new Intent(Hello.this,Hi.class));
            }
        });

    }
}
