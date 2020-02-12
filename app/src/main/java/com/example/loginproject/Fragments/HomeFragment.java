package com.example.loginproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.loginproject.Adapters.SliderAdapter;
import com.example.loginproject.Adapters.HomeGridAdapter;
import com.example.loginproject.Models.HomeGridModel;
import com.example.loginproject.Models.SliderAds;
import com.example.loginproject.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ViewPager mSliderViewPager ;
//    private TabLayout mTabLayout;


    private SliderAdapter sliderAdapter ;
    private ArrayList<SliderAds> arrayList1;

    private CircleIndicator circleIndicator ;
    private GridView gridView ;
    private HomeGridAdapter homeGridAdapter ;
    private ArrayList<HomeGridModel> arrayListGrid;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);

        assert container != null;
        final Context thiscontext = container.getContext();
        mSliderViewPager=(ViewPager) view.findViewById(R.id.slideViewPagerID);
    //    mTabLayout=view.findViewById(R.id.tabDots);
        arrayList1=new ArrayList<>();
        arrayList1.add(new SliderAds(R.drawable.eat,"Eat"));
        arrayList1.add(new SliderAds(R.drawable.sleep,"Sleep"));
        arrayList1.add(new SliderAds(R.drawable.code,"Code"));
        sliderAdapter=new SliderAdapter(thiscontext,arrayList1);
        mSliderViewPager.setAdapter(sliderAdapter);
     //   mTabLayout.setupWithViewPager(mSliderViewPager,true);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.home_CircleindicatorId);
        circleIndicator.setViewPager(mSliderViewPager);

        gridView=view.findViewById(R.id.home_gridViewId);
        arrayListGrid=new ArrayList<>();
        arrayListGrid.add(new HomeGridModel(R.drawable.sleep));
        arrayListGrid.add(new HomeGridModel(R.drawable.eat));
        arrayListGrid.add(new HomeGridModel(R.drawable.code));
        arrayListGrid.add(new HomeGridModel(R.drawable.ic_launcher_background));
        homeGridAdapter=new HomeGridAdapter(arrayListGrid, thiscontext);
        gridView.setAdapter(homeGridAdapter);


        assert getFragmentManager() != null;
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction() ;

        final AttendanceFragment attendanceFragment =new AttendanceFragment();
        final DietFragment dietFragment =new DietFragment();
        final DailyWorkoutFragment dailyWorkoutFragment=new DailyWorkoutFragment();
        final EventsFragment eventsFragment =new EventsFragment();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(arrayListGrid.get(position).getImageGrid()) {
                    case R.drawable.sleep :
                        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(container.getId(),attendanceFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;

                    case R.drawable.eat :
                        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(container.getId(),dietFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit() ;
                        break;
                    case R.drawable.code :
                        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(container.getId(),dailyWorkoutFragment);
                        fragmentTransaction.addToBackStack(null);

                        fragmentTransaction.commit() ;
                        break;

                    case R.drawable.ic_launcher_background :
                        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(container.getId(),eventsFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit() ;
                        break;
                }

            }
        });
        return view ;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        mSliderViewPager=(ViewPager) mSliderViewPager.findViewById();
//
////        bottomNavigationView=findViewById(R.id.bottomNavgationView_homeID);
//        arrayList1=new ArrayList<>();
//        arrayList1.add(new SliderAds(R.drawable.eat,"Eat"));
//        arrayList1.add(new SliderAds(R.drawable.sleep,"Sleep"));
//        arrayList1.add(new SliderAds(R.drawable.code,"Code"));
//
//
////        sliderAdapter=new SliderAdapter(HomeFragment.this,arrayList1);
//
//        mSliderViewPager.setAdapter(sliderAdapter);


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}

