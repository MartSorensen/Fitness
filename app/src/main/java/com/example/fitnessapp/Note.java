package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String weight;
    private int repetitions;

    public Note(String title, String weight, int repetitions) {
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
