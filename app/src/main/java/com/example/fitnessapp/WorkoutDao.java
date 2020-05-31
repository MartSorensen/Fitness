package com.example.fitnessapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    void insert(Workout workout);

    @Update
    void update(Workout workout);

    @Delete
    void delete(Workout workout);

    @Query("SELECT * FROM workout_table ORDER BY title DESC")
    LiveData<List<Workout>> getAllWorkouts();

    @Query("SELECT title, MAX(weight), weight, repetitions, id FROM workout_table GROUP BY title")
    LiveData<List<Workout>> getDistinctTitles();
}
