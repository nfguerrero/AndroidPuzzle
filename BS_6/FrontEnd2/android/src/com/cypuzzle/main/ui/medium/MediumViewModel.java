package com.cypuzzle.main.ui.medium;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MediumViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MediumViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the medium leaderboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}