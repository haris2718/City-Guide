package com.example.kastoria_guide.Sorting;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.SharedPreferences;
import android.location.Location;

import java.util.Collections;
import java.util.List;

import com.example.kastoria_guide.Model.Place;

public class Sorting {
    private SharedPreferences sharePrefPlaceActivity;
    private SharedPreferences.Editor myEditorPlaceActivity;
    private List<Place> list;
    private float wayLatitude,wayLongitude;

    public Sorting(List<Place> list, Application application, float wayLatitude, float wayLongitude) {
        this.wayLatitude=wayLatitude;
        this.wayLongitude=wayLongitude;
        sharePrefPlaceActivity = application.getSharedPreferences("com.example.kastoria_guide.data_prefs_file", MODE_PRIVATE);
        myEditorPlaceActivity = sharePrefPlaceActivity.edit();


        this.list = sorting(list);
    }

    public List<Place> getList() {
        return list;
    }



    public List<Place> sorting(List<Place> list) {
        setDistance(list);

        if (sharePrefPlaceActivity != null) {
            if (sharePrefPlaceActivity.getInt("sorting_variable", 0) == 1) {
                Collections.sort(list, Place.PlaceDistanceASCComparetor);
                return list;
            } else {
                Collections.sort(list, Place.PlaceNameAZComparetor);
                return list;
            }
        } else {
            //εάν δεν υπάρχει η μεταβλητή sorting_variable
            myEditorPlaceActivity.putInt("sorting_variable", 1);
            myEditorPlaceActivity.commit();
            return list;
        }


    }

    private double get_distance(float wayLatitude,float wayLongitude,float lat2,float lon2){
        Location curentLocation=new Location("");
        curentLocation.setLatitude(wayLatitude);
        curentLocation.setLongitude(wayLongitude);
        Location hotelLocation = new Location("");
        hotelLocation.setLatitude(lat2);
        hotelLocation.setLongitude(lon2);
        return curentLocation.distanceTo(hotelLocation)/1000 ;
    }

    public List<Place> setDistance(List<Place> placeList) {
        for (Place p : placeList) {
            p.setDistance((float) get_distance(wayLatitude, wayLongitude, p.getLat(), p.getLon()));
        }
        return placeList;
    }


}
