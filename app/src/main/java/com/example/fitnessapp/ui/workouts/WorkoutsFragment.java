package com.example.fitnessapp.ui.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fitnessapp.R;

public class WorkoutsFragment extends Fragment {

    private WorkoutsViewModel workoutsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        workoutsViewModel =
                ViewModelProviders.of(this).get(WorkoutsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_workouts, container, false);
        final TextView textView = root.findViewById(R.id.text_workouts);
        workoutsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
