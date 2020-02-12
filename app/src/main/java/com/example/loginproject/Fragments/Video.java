package com.example.loginproject.Fragments;


import android.content.Context;
//import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.loginproject.Activities.VideoPlayerRecyclerView;
import com.example.loginproject.Adapters.VideoPlayerRecyclerAdapter;
import com.example.loginproject.Models.MediaObject;
import com.example.loginproject.R;
import com.example.loginproject.Utils.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.loginproject.Utils.Resources ;

/**
 * A simple {@link Fragment} subclass.
 */
public class Video extends Fragment {
    private VideoPlayerRecyclerView mRecyclerView;

    public Video() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_video,container,false);

        final Context thisContext= container.getContext();

        mRecyclerView = view.findViewById(R.id.recycler_view);

        initRecyclerView(thisContext);

//        final Context thisContext = view.findViewById(R.id.)



        return view;
    }

    private void initRecyclerView(Context thisContext){
        LinearLayoutManager layoutManager = new LinearLayoutManager(thisContext);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        ArrayList<MediaObject> mediaObjects = new ArrayList<MediaObject>(Arrays.asList(Resources.MEDIA_OBJECTS));
        mRecyclerView.setMediaObjects(mediaObjects);
        VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(mediaObjects, initGlide());
        mRecyclerView.setAdapter(adapter);
    }


    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }


    @Override
    public void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }


}
