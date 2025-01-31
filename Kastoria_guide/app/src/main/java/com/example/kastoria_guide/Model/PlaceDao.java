package com.example.kastoria_guide.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//The @Dao annotation identifies it as a DAO class for Room.
@Dao
public interface PlaceDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    //The @Insert annotation is a special DAO method annotation where you don't have to provide any
    // SQL! (There are also @Delete and @Update annotations for deleting and updating rows,
    // but you are not using them in this app.)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Place place);

    @Update
    void update(Place place);

    @Query("DELETE  FROM Place_table")
    void deleteAll();

    @Query("SELECT * FROM Place_table ORDER BY name ASC")
    LiveData<List<Place>> getAlphabetizedWords();

    @Query("SELECT * FROM Place_table WHERE category =:category ORDER BY name ASC")
    LiveData<List<Place>> getPlaceByCategory(String category);

    @Query("SELECT * FROM Place_table WHERE category =:category ORDER BY distance ASC")
    LiveData<List<Place>> getPlaceByCategoryAndDistance(String category);
}
