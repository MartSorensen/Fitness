package com.example.fitnessapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Workout.class, version = 1, exportSchema = false)
public abstract class WorkoutDatabase extends RoomDatabase {

    //Used to access singleton database
    private static WorkoutDatabase instance;

    public abstract WorkoutDao workoutDao();

    // synchronized avoids multiple treads from accessing the method aka makes sure we only gets one instance of the database
    public static  synchronized WorkoutDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WorkoutDatabase.class, "workout_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
