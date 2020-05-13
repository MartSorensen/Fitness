package com.example.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GoalsFragment extends Fragment {
    private static final String TAG = "GoalsFragment";

    private Button btnNavHome;
    private Button btnNavWorkouts;
    private Button btnNavProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        btnNavProgress = (Button) view.findViewById(R.id.btnNavProgress);
        btnNavWorkouts = (Button) view.findViewById(R.id.btnNavWorkouts);

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
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
        return view;
    }
}
