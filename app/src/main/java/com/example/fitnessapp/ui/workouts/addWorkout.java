package com.example.fitnessapp.ui.workouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fitnessapp.R;

public class addWorkout extends AppCompatActivity {

    public String benchKg = "0";
    public String benchReps = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);
    }


    public void getUserInputBench(View view) {

        System.out.println("submitBenchpress");

        EditText editText = (EditText) findViewById(R.id.amountOfKgBench);
        EditText editText1 = (EditText) findViewById(R.id.benchReps);
        benchKg = editText.getText().toString();
        benchReps = editText1.getText().toString();


    }

}
