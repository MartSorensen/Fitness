package com.example.fitnessapp.ui.rutines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RutinesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RutinesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rutines fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}