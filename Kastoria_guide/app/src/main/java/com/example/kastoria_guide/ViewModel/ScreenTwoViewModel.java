package com.example.kastoria_guide.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import com.example.kastoria_guide.Location.LocationApp;
import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.Model.PlaceRepository;
import com.example.kastoria_guide.Retrofit.RemoteUpdateDB;
import com.example.kastoria_guide.Sorting.Sorting;


public class ScreenTwoViewModel extends AndroidViewModel {

    //Added a private member variable to hold a reference to the repository.
    private PlaceRepository mRepository;
    private final LiveData<List<Place>> mAllPlaces;
    private LiveData<List<Place>> mPlaceByCategory;
    private LiveData<List<Place>> mPlaceByCategoryAndDistance;
    private LocationApp locationApp;
    private Sorting sorting;
    RemoteUpdateDB updateDB;
    static int isfirsttime=0;

    //Created a class called WordViewModel that gets the Application as a parameter
// and extends AndroidViewModel.
    public ScreenTwoViewModel(Application application) {
        super(application);
        if(isfirsttime==0){
            updateDB=new RemoteUpdateDB(application);
            updateDB.loadPlaces();
            isfirsttime+=1;
        }
        locationApp=LocationApp.getInstance(application);
        /*sharePrefPlaceActivity = getApplication().getSharedPreferences("com.example.kcityguide.data_prefs_file", MODE_PRIVATE);
        myEditorPlaceActivity = sharePrefPlaceActivity.edit();*/
        mRepository = PlaceRepository.getInstance(application);
//Added a getAllWords() method to return a cached list of words.
//In the constructor, initialized the allWords LiveData using the repository.
        mAllPlaces = mRepository.getAllPlaces();
    }

    public LiveData<List<Place>> getmPlaceByCategory(String category) {
        mPlaceByCategory = mRepository.getPlacesByCategory(category);
        return  mPlaceByCategory;
    }


    public LiveData<List<Place>> getmPlaceByCategoryAndDistance(String category) {
        mPlaceByCategoryAndDistance = mRepository.getPlacesByCategoryAndDistance(category);

        return mPlaceByCategoryAndDistance;
    }

    LiveData<List<Place>> getmAllPlaces() {
        return mAllPlaces;
    }

    //Created a wrapper insert() method that calls the Repository's insert() method.
// In this way, the implementation of insert() is encapsulated from the UI.
    public void insert(Place place) {
        mRepository.insert(place);
    }

    public LocationApp getLocationApp(){
        return locationApp;
    }

        public void StartLocationupdates(){

            locationApp.startLocationUpdates();
        }

    public void startLocationUpdate(){
        locationApp.startLocationUpdates();
    }

}