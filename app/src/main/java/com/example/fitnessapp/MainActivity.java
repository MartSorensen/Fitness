package com.example.fitnessapp;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
   // public static final int ADD_NOTE_REQUEST = 1;
    private static final String TAG = "MainActivity";

    private SectionsStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;


    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSectionStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.containter);
        //setup the pager
        setupViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "HomeFragment");
        adapter.addFragment(new WorkoutFragment(), "WorkoutFragment");
        adapter.addFragment(new ProgressFragment(), "ProgressFragment");
        adapter.addFragment(new MaxLiftsFragment(), "MaxLiftsFragment");
        adapter.addFragment(new GoalsFragment(), "GoalsFragments");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

}
