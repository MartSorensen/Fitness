package com.example.fitnessapp.ui.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkoutsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> maddWorkoutButton;

    public WorkoutsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is workouts fragment");
        maddWorkoutButton = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }


}