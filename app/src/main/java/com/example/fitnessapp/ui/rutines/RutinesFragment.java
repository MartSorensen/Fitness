package com.example.fitnessapp.ui.rutines;

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

public class RutinesFragment extends Fragment {

    private RutinesViewModel rutinesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rutinesViewModel =
                ViewModelProviders.of(this).get(RutinesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rutines, container, false);
        final TextView textView = root.findViewById(R.id.text_rutines);
        rutinesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
