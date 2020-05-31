package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private Button btnNavWorkouts;
    private Button btnNavMaxLifts;
    private Button btnNavGoals;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnNavWorkouts = (Button) view.findViewById(R.id.btnNavWorkouts);
        btnNavMaxLifts = (Button) view.findViewById(R.id.btnNavMaxLifts);
        btnNavGoals = (Button) view.findViewById(R.id.btnNavGoals);


        btnNavWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });
        btnNavMaxLifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });
        btnNavGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalsView.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
