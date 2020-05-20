package com.example.fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditWorkoutActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.fitnessapp.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.fitnessapp.EXTRA_TITLE";
    public static final String EXTRA_WEIGHT =
            "com.example.fitnessapp.EXTRA_WEIGHT";
    public static final String EXTRA_REPETITIONS =
            "com.example.fitnessapp.EXTRA_REPETITIONS";

    private EditText editTextTitle;
    private EditText editTextWeight;
    private NumberPicker numberPickerRepetitions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextWeight = findViewById(R.id.edit_text_weight);
        numberPickerRepetitions = findViewById(R.id.number_picker_repetitions);

        numberPickerRepetitions.setMinValue(1);
        numberPickerRepetitions.setMaxValue(20);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Workout");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextWeight.setText(intent.getStringExtra(EXTRA_WEIGHT));
            numberPickerRepetitions.setValue(intent.getIntExtra(EXTRA_REPETITIONS, 1));
        } else {
            setTitle("Add Workout");
        }
    }

    private  void saveWorkout() {
        String title = editTextTitle.getText().toString();
        String weight = editTextWeight.getText().toString();
        int repetitions = numberPickerRepetitions.getValue();

        if (title.trim().isEmpty() || weight.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_WEIGHT, weight);
        data.putExtra(EXTRA_REPETITIONS, repetitions);

        int id = getIntent().getIntExtra(EXTRA_ID, 1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_workout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_workout:
                saveWorkout();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
