package com.example.loginproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.loginproject.Models.SliderAds;
import com.example.loginproject.R;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater;
    ArrayList<SliderAds>arrayList ;


    public SliderAdapter(Context context,ArrayList<SliderAds> arrayList){
        this.context=context ;
        this.arrayList = arrayList;
    }

//    public static int[] slide_images={
//            R.drawable.eat,
//            R.drawable.sleep,
//            R.drawable.code
//    };
//
//    public String[] slide_headings={
//            "EAT",
//            "SLEEP",
//            "CODE"
//    };

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=(ImageView)view.findViewById(R.id.imageView_slideID);
        TextView slideHeading =(TextView)view.findViewById(R.id.textView_slideID);

        slideImageView.setImageResource( arrayList.get(position).getImagageSlide() );

//        slideHeading.setText(slide_headings[position]);
        slideHeading.setText( arrayList.get(position).getHeadingSlide() );

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
