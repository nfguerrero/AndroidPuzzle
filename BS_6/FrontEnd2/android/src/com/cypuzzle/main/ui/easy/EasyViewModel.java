package com.cypuzzle.main.ui.easy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EasyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EasyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the easy leaderboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}