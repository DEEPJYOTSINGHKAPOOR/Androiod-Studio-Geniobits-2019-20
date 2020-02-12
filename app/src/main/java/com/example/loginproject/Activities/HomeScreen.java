package com.example.loginproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.loginproject.Adapters.SliderAdapter;
import com.example.loginproject.Fragments.BlogFragment;
import com.example.loginproject.Fragments.HomeFragment;
import com.example.loginproject.Fragments.Settings;
import com.example.loginproject.Fragments.Video;
import com.example.loginproject.Models.SliderAds;
import com.example.loginproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

//    private static final String TAG = "HomeScreen";

    private static final String TAG = "HomeScreen";
    private ViewPager mSliderViewPager ;
    

    private LinearLayout mDotLayout ;

    private SliderAdapter sliderAdapter ;

    private BottomNavigationView bottomNavigationView ;

    private ArrayList<SliderAds>arrayList1;

    public static int HOME_NAVIGATION_BUTTON ;

//    private static  int currentPage =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mSliderViewPager=(ViewPager) findViewById(R.id.slideViewPagerID);


        Log.d(TAG, "onCreate: ");

//        arrayList1=new ArrayList<>();
//        arrayList1.add(new SliderAds(R.drawable.eat,"Eat"));
//        arrayList1.add(new SliderAds(R.drawable.sleep,"Sleep"));
//        arrayList1.add(new SliderAds(R.drawable.code,"Code"));


//        sliderAdapter=new SliderAdapter(HomeScreen.this,arrayList1);

//        mSliderViewPager.setAdapter(sliderAdapter);

        bottomNavigationView=findViewById(R.id.bottomNavgationView_homeID);

        bottomNavigationView.setOnNavigationItemSelectedListener(HomeScreen.this);

        bottomNavigationView.setSelectedItemId(R.id.home_bottomID);
    }
    HomeFragment homeFragment=new HomeFragment() ;
    BlogFragment blogFragment =new BlogFragment();
    Video videoFragment=new Video() ;
    Settings settingsFragment=new Settings();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.i(TAG,"onNavigationItemSelected : ");
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction() ;
        switch(menuItem.getItemId()){
            case R.id.home_bottomID:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.containerFrame_homeID,homeFragment);
 //               fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit() ;
                return true ;
            case R.id.blog_bottomID:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.containerFrame_homeID,blogFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true ;

            case R.id.video_bottomID:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.containerFrame_homeID,videoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;

            case R.id.settings_bottomID:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.containerFrame_homeID,settingsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
        }
        return false; //returns false means item selected has not been handled.
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        bottomNavigationView.setSelectedItemId(R.id.home_bottomID);
    }
}