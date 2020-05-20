package com.example.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MaxLiftsFragment extends Fragment {
    private static final String TAG = "MaxLiftsFragment";

    private Button btnNavHome;

    private WorkoutViewModel workoutViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_max_lifts, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        //Database recyclerView display
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_maxLifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        final WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
        workoutViewModel.getAllDistinctTitles().observe(this, new Observer<List<Workout>>() {
            //will only get called if the activity is in the foreground and will get destroyed after usage
            @Override
            public void onChanged(List<Workout> workouts) {
                //update view
                adapter.setWorkouts(workouts);
            }
        });

        return view;
    }
}
