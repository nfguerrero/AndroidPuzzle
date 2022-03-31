package com.cypuzzle.main.ui.hard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the hard leaderboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}