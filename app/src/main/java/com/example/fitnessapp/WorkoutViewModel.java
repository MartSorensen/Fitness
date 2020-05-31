package com.example.fitnessapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository repository;
    private LiveData<List<Workout>> allWorkouts;
    private LiveData<List<Workout>> allDistinctTitles;


    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        repository = new WorkoutRepository(application);
        allWorkouts = repository.getAllWorkouts();
        allDistinctTitles = repository.getDistinctTitles();
    }

    public void insert(Workout workout) {
        repository.insert(workout);
    }

    public void update(Workout workout) {
        repository.update(workout);
    }

    public void delete(Workout workout) {
        repository.delete(workout);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public LiveData<List<Workout>> getAllDistinctTitles() {
        return allDistinctTitles;
    }
}
