package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class GoalsView extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GoalsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
  public static ArrayList<GoalsItems> goalsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_view);
        buildRecyclerView();

        Button addNewWorkoutBtn = (Button)findViewById(R.id.addNewGoalButton);
        addNewWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), com.example.fitnessapp.AddNewGoal.class);
                startActivity(startIntent);

            }
        });
        Button clearWorkoutsBtn = (Button)findViewById(R.id.clearGoals);
        clearWorkoutsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalsList.clear();
                mAdapter.notifyDataSetChanged();

            }
        });

    }
    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GoalsAdapter(goalsList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new GoalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                goalsList.get(position);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }


        });
    }
    public void removeItem(int position){
        goalsList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }



}
