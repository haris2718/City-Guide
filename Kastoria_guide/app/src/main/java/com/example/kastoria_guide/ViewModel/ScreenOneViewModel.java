package com.example.kastoria_guide.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.Model.PlaceRepository;

public class ScreenOneViewModel extends AndroidViewModel {
//Added a private member variable to hold a reference to the repository.
    private PlaceRepository mRepository;

    private final LiveData<List<Place>> mAllPlaces;
//Created a class called WordViewModel that gets the Application as a parameter
// and extends AndroidViewModel.
    public ScreenOneViewModel(Application application) {
        super(application);
        mRepository = PlaceRepository.getInstance(application);
//Added a getAllWords() method to return a cached list of words.
//In the constructor, initialized the allWords LiveData using the repository.
        mAllPlaces = mRepository.getAllPlaces();
    }

    LiveData<List<Place>> getmAllPlaces() { return mAllPlaces; }
//Created a wrapper insert() method that calls the Repository's insert() method.
// In this way, the implementation of insert() is encapsulated from the UI.
    public void insert(Place place) { mRepository.insert(place); }
}


