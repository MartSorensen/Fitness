/*This class works as an API/abstraction layer for accessing the different data sources
its still considered good practice eventhough it only contain data from the database*/

package com.example.fitnessapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkoutRepository {

    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> allWorkouts;
    private LiveData<List<Workout>> allDistinctTitles;

    public WorkoutRepository(Application application) {
        WorkoutDatabase database = WorkoutDatabase.getInstance(application);
        workoutDao = database.workoutDao();
        allWorkouts = workoutDao.getAllWorkouts();
        allDistinctTitles = workoutDao.getDistinctTitles();
    }

    public void insert(Workout workout) {
        new InsertWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public void update(Workout workout) {
        new UpdateWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public void delete(Workout workout) {
        new DeleteWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public LiveData<List<Workout>> getDistinctTitles() {return allDistinctTitles; }

    //It has to be static so it doesn't have a reference to the repository else it can cause memory leaks
    private static class InsertWorkoutAsyncTask extends AsyncTask<Workout, Void, Void> {
        private WorkoutDao workoutDao;

        private InsertWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.insert(workouts[0]);
            return null;
        }
    }

    private static class UpdateWorkoutAsyncTask extends AsyncTask<Workout, Void, Void> {
        private WorkoutDao workoutDao;

        private UpdateWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.update(workouts[0]);
            return null;
        }
    }

    private static class DeleteWorkoutAsyncTask extends AsyncTask<Workout, Void, Void> {
        private WorkoutDao workoutDao;

        private DeleteWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.delete(workouts[0]);
            return null;
        }
    }
}
