package com.henry.tinnews.ui.save;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.henry.tinnews.model.NewsResponse;
import com.henry.tinnews.repository.NewsRepository;

public class SaveViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SaveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}