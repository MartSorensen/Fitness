package com.example.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private static final String TAG = "GoalsFragment";

    private Button btnNavWorkouts;
    private Button btnNavProgress;
    private Button btnNavMaxLifts;
    private Button btnNavGoals;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnNavWorkouts = (Button) view.findViewById(R.id.btnNavWorkouts);
        btnNavProgress = (Button) view.findViewById(R.id.btnNavProgress);
        btnNavMaxLifts = (Button) view.findViewById(R.id.btnNavMaxLifts);
        btnNavGoals = (Button) view.findViewById(R.id.btnNavGoals);


        btnNavWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });
        btnNavProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });
        btnNavMaxLifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(3);
            }
        });
        btnNavGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(4);
            }
        });

        return view;
    }
}
