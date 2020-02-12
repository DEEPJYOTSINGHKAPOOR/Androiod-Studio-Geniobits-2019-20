//package com.example.loginproject.Models;
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.Volley;
//
//public class MySingleton {
//
//    private static MySingleton mInstance =null;
//
//    private RequestQueue requestQueue ;
//
//    public static Context mContext ;
//
//    private MySingleton(Context context){
//        mContext=context ;
//        requestQueue=getRequestQueue();
//    }
//
//    public RequestQueue getRequestQueue(){
//        if(requestQueue==null){
//            requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
//        }
//        return requestQueue ;
//    }
//
//    public static synchronized MySingleton getInstance(Context context)
//    {
//        if(mInstance==null){
//            mInstance= new MySingleton(context);
//        }
//        return mInstance ;
//    }
//
//    public<T> void addToRequestQueue(Request<T> request)
//    {
//        requestQueue.add(request);
//    }
//}