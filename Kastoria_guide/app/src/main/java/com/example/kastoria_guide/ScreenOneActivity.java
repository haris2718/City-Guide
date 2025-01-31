package com.example.kastoria_guide;

import static com.example.kastoria_guide.Model.Place.PlaceDistanceASCComparetor;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import com.example.kastoria_guide.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GestureDetectorCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import com.example.kastoria_guide.Gesture.MyGestureListener;
import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.ViewModel.ScreenTwoViewModel;

public class ScreenOneActivity<category> extends AppCompatActivity  {
    private RecyclerView RecyclerViewPlace;
    private RecyclerViewAdapter madapter;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    private ScreenTwoViewModel mScreenTwoViewModel;
    //private ScreenOneViewModel mScreenOneViewModel;
    private String Category;
    private Intent intent;
    private float lat,lon;
    private List<Place> plList;
    private SharedPreferences sharePrefPlaceActivity;
    private SharedPreferences.Editor myEditorPlaceActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePrefPlaceActivity =getSharedPreferences("com.example.kastoria_guide.data_prefs_file", MODE_PRIVATE);
        myEditorPlaceActivity = sharePrefPlaceActivity.edit();


        //Η μορφή που θα έχει η Activity
        setContentView(R.layout.activity_screen_one);
        intent= getIntent();
        Bundle bundle = intent.getExtras();
        Category=(String)bundle.get("category");
        RecyclerViewPlace = findViewById(R.id.RecyclerViewPlace);
        madapter = new RecyclerViewAdapter(this);

        if (ActivityCompat.checkSelfPermission(ScreenOneActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(ScreenOneActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            //when permission denied
            ActivityCompat.requestPermissions(ScreenOneActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_FINE_LOCATION);



        }
        mScreenTwoViewModel=new ViewModelProvider(this).get(ScreenTwoViewModel.class);
        mScreenTwoViewModel.startLocationUpdate();

        if (sharePrefPlaceActivity.getInt("sorting_variable", 1) == 1){
            mScreenTwoViewModel.getmPlaceByCategoryAndDistance(Category).observe(this, places -> {
                // Update the cached copy of the words in the adapter.
                //εδώ θα γίνει το sorting
//            Toast.makeText(ScreenOneActivity.this,
//                    "lon:"+lon +" lat:" + lat,Toast.LENGTH_LONG).show();
                mScreenTwoViewModel.getLocationApp().observe(this,
                        curentLocation->{
                            lat=curentLocation[0];
                            lon=curentLocation[1];
//                        Toast.makeText(ScreenOneActivity.this,
//                                "lat activity "+lat,Toast.LENGTH_SHORT).show();
                        plList=setDistance(places,curentLocation[0],curentLocation[1]);
                        Collections.sort(plList, PlaceDistanceASCComparetor);
                        madapter.setPlaceList(plList);

                        });


            });
        }else {
            mScreenTwoViewModel.getmPlaceByCategory(Category).observe(this, places -> {
                // Update the cached copy of the words in the adapter.
                //εδώ θα γίνει το sorting
//            Toast.makeText(ScreenOneActivity.this,
//                    "lon:"+lon +" lat:" + lat,Toast.LENGTH_LONG).show();
                mScreenTwoViewModel.getLocationApp().observe(this,
                        curentLocation->{
                            lat=curentLocation[0];
                            lon=curentLocation[1];
//                        Toast.makeText(ScreenOneActivity.this,
//                                "lat activity "+lat,Toast.LENGTH_SHORT).show();

                            madapter.setPlaceList(setDistance(places,curentLocation[0],curentLocation[1]));

                        });


            });
        }


        RecyclerViewPlace.setAdapter(madapter);
        RecyclerViewPlace.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewPlace.hasFixedSize();

    }//end on create

    public List<Place>  setDistance (List<Place> placeList,float lat,float lon){
        for (Place place:placeList) {
            place.setDistance(get_distance(lat,lon,place.getLat(), place.getLon()));
//            Toast.makeText(ScreenOneActivity.this,
//                    "dinstance: "+get_distance(lat,lon,place.getLat(), place.getLon()),Toast.LENGTH_LONG).show();
        }
        return placeList;
    }

    private float get_distance(float wayLatitude,float wayLongitude,float lat2,float lon2){
        Location curentLocation=new Location("");
        curentLocation.setLatitude(wayLatitude);
        curentLocation.setLongitude(wayLongitude);
        Location hotelLocation = new Location("");
        hotelLocation.setLatitude(lat2);
        hotelLocation.setLongitude(lon2);
        return curentLocation.distanceTo(hotelLocation)/1000 ;
    }


}