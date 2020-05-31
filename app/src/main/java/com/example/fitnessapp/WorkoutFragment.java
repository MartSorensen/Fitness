package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class WorkoutFragment extends Fragment {
    private static final String TAG = "WorkoutFragment";
    public static final int ADD_WORKOUT_REQUEST = 1;
    public static final int EDIT_WORKOUT_REQUEST = 2;

    private Button btnNavHome;

    private WorkoutViewModel workoutViewModel;

    private RecyclerView mRecyclerView;
    private GoalsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<GoalsItems> workoutsList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);

       FloatingActionButton buttonAddWorkout = view.findViewById(R.id.button_add_workout);
        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditWorkoutActivity.class);
                startActivityForResult(intent, ADD_WORKOUT_REQUEST);
            }
        });
        //Database recyclerView display
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_workout);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        final WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
        workoutViewModel.getAllWorkouts().observe(this, new Observer<List<Workout>>() {
            //will only get called if the activity is in the foreground and will get destroyed after usage
            @Override
            public void onChanged(List<Workout> workouts) {
                //update view
                adapter.setWorkouts(workouts);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override   //used for drag and drop
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                workoutViewModel.delete(adapter.getWorkoutAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(),"Workout deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new WorkoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Workout workout) {
                Intent intent = new Intent(getActivity(), AddEditWorkoutActivity.class);
                intent.putExtra(AddEditWorkoutActivity.EXTRA_ID, workout.getId());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_TITLE, workout.getTitle());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_WEIGHT, workout.getWeight());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_REPETITIONS, workout.getRepetitions());
                startActivityForResult(intent, EDIT_WORKOUT_REQUEST);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_WORKOUT_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditWorkoutActivity.EXTRA_TITLE);
            String weight = data.getStringExtra(AddEditWorkoutActivity.EXTRA_WEIGHT);
            int repetitions = data.getIntExtra(AddEditWorkoutActivity.EXTRA_REPETITIONS, 1);

            Workout workout = new Workout(title, weight, repetitions);
            workoutViewModel.insert(workout);

            Toast.makeText(getContext(), "Workout saved", Toast.LENGTH_SHORT).show();
        }  else if(requestCode == EDIT_WORKOUT_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddEditWorkoutActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Workout Can't be updated", Toast.LENGTH_SHORT).show();
            }

            String title = data.getStringExtra(AddEditWorkoutActivity.EXTRA_TITLE);
            String weight = data.getStringExtra(AddEditWorkoutActivity.EXTRA_WEIGHT);
            int repetitions = data.getIntExtra(AddEditWorkoutActivity.EXTRA_REPETITIONS, 1);

            Workout workout = new Workout(title, weight, repetitions);
            workout.setId(id);
            workoutViewModel.update(workout);
            Toast.makeText(getContext(), "Workout updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Workout not saved", Toast.LENGTH_SHORT).show();
        }
    }
}

