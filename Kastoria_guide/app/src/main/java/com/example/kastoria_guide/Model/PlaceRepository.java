package com.example.kastoria_guide.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PlaceRepository {
    /*
    The DAO is passed into the repository constructor as opposed to the whole database.
    This is because you only need access to the DAO,
    since it contains all the read/write methods for the database.
    There's no need to expose the entire database to the repository.
     */
    private static volatile PlaceRepository INSTANCE;
    private PlaceDao mPlaceDao;
    private LiveData<List<Place>> mAllPlaces;
    private LiveData<List<Place>> mPlacesByCategory;
    private LiveData<List<Place>> mPlacesByCategoryAndDistance;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    private PlaceRepository(Application application) {
        PlaceRoomDatabase db = PlaceRoomDatabase.getDatabase(application);
        mPlaceDao = db.PlaceDao();
        mAllPlaces =  mPlaceDao.getAlphabetizedWords();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    /*
    The getAllWords method returns the LiveData list of words from Room; we can do this because of
    how we defined the getAlphabetizedWords method to return LiveData in the
     "The LiveData class" step.
     Room executes all queries on a separate thread.
     Then observed LiveData will notify the observer on the main thread
      when the data has changed.
     */
     public static PlaceRepository getInstance(Application application){
         if(INSTANCE == null) {
             synchronized (PlaceRepository.class) {
                 if (INSTANCE == null) {
                     INSTANCE = new PlaceRepository(application);
                 }
             }
         }
         return INSTANCE;

     }
    public LiveData<List<Place>> getAllPlaces() {
        return mAllPlaces;
    }

    public LiveData<List<Place>> getPlacesByCategory(String category){
        mPlacesByCategory=  mPlaceDao.getPlaceByCategory(category);
        return mPlacesByCategory;
    }

    public LiveData<List<Place>> getPlacesByCategoryAndDistance(String category){
        mPlacesByCategoryAndDistance= mPlaceDao.getPlaceByCategoryAndDistance(category);
        return mPlacesByCategoryAndDistance;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Place place) {
        PlaceRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPlaceDao.insert(place);
        });
    }

    public void update(Place place) {
        PlaceRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPlaceDao.update(place);
        });

    }

    public void deleteAll(){
        PlaceRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPlaceDao.deleteAll();
        });
    }

}


