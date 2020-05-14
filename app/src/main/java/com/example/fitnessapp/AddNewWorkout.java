package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewWorkout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_workout);
    }
    public void enterWorkoutBenchpress(View view){
        EditText editText = (EditText) findViewById(R.id.amountOfKgBench);
        EditText editText2 = (EditText) findViewById(R.id.amountOfRepsBench);
        String kg = editText.getText().toString();
        String reps = editText2.getText().toString();
        workoutsView.workoutsList.add(new WorkoutsItems(R.drawable.bench_press, kg+" kg", reps+"reps"));


    }
    public void enterWorkoutSquat(View view){
        EditText editText = (EditText) findViewById(R.id.amountOfKgSquat);
        EditText editText2 = (EditText) findViewById(R.id.amountOfRepsSquat);
        String kg = editText.getText().toString();
        String reps = editText2.getText().toString();
        workoutsView.workoutsList.add(new WorkoutsItems(R.drawable.squat, kg+" kg", reps+"reps"));


    }
    public void goBack(View view){
        Intent intent = new Intent(this, workoutsView.class);
        startActivity(intent);
    }

}
