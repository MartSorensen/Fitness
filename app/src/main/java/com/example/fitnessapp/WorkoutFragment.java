package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment {
    private static final String TAG = "WorkoutFragment";

    private Button btnNavHome;

    private NoteViewModel noteViewModel;

    private RecyclerView mRecyclerView;
    private WorkoutsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<WorkoutsItems> workoutsList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        Button addNewWorkoutBtn = (Button)view.findViewById(R.id.addNewWorkoutButton);
       // buildRecyclerView();

        //Database recyclerView display
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            //will only get called if the activity is in the foreground and will get destroyed after usage
            @Override
            public void onChanged(List<Note> notes) {
                //update view
                adapter.setNotes(notes);
            }
        });

        addNewWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), AddNewWorkout.class);
                startActivity(startIntent);

            }
        });
        Button clearWorkoutsBtn = (Button)view.findViewById(R.id.clearWorkouts);
        clearWorkoutsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutsList.clear();
                mAdapter.notifyDataSetChanged();

            }
        });

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        return view;
    }

    /*public void buildRecyclerView(){
        mRecyclerView = getActivity().findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mAdapter = new WorkoutsAdapter(workoutsList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new WorkoutsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                workoutsList.get(position);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }


        });
    }
    public void removeItem(int position){
        workoutsList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }*/
}

