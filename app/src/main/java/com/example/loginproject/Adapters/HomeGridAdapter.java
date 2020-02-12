package com.example.loginproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.fragment.app.FragmentTransaction;

import com.example.loginproject.Models.HomeGridModel;
import com.example.loginproject.R;

import java.util.ArrayList;

public class HomeGridAdapter extends BaseAdapter {
    private ArrayList<HomeGridModel> arrayList ;
    private Context mContext ;
    private LayoutInflater mLayoutInflater ;
    private ImageView imageView;
    public HomeGridAdapter(ArrayList<HomeGridModel> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;

        mLayoutInflater=LayoutInflater.from(mContext);
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view ;
        if(convertView==null){
            view=mLayoutInflater.inflate(R.layout.fit_in_grid_home,parent,false);


            imageView=view.findViewById(R.id.fitInGrid_imageId);
            imageView.setImageResource(arrayList.get(position).getImageGrid());

        }else{
            view=convertView;
        }


        return view;
    }
}
