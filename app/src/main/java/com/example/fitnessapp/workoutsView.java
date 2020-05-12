package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class workoutsView extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private WorkoutsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
  public static ArrayList<com.example.fitnessapp.WorkoutsItems> workoutsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_view);
       // workoutsList.add(new WorkoutsItems(R.drawable.bench_press, "0 kg", "0 Rep"));
        buildRecyclerView();

        Button addNewWorkoutBtn = (Button)findViewById(R.id.addNewWorkoutButton);
        addNewWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), com.example.fitnessapp.AddNewWorkout.class);
                startActivity(startIntent);

            }
        });
        Button clearWorkoutsBtn = (Button)findViewById(R.id.clearWorkouts);
        clearWorkoutsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutsList.clear();
                mAdapter.notifyDataSetChanged();

            }
        });

    }
    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
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
    }



}
