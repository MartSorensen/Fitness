package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProgressFragment extends Fragment {
    private static final String TAG = "ProgressFragment";

    private Button btnNavHome;
    private Button btnNavGoals;
    private Button btnNavWorkoutsActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        btnNavGoals = (Button) view.findViewById(R.id.btnNavGoals);
        btnNavWorkoutsActivity = (Button) view.findViewById(R.id.btnNavWorkoutsActivity);

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Home Screen", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        btnNavGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Goals Screen", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });
        btnNavWorkoutsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Workouts", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), workoutsView.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
