package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_table")
public class Workout {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String weight;
    private int repetitions;

    public Workout(String title, String weight, int repetitions) {
        this.title = title;
        this.weight = weight;
        this.repetitions = repetitions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWeight() {
        return weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

}
