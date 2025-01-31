package com.example.kastoria_guide.Location;




import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationApp extends LiveData<float[]> {
    private static volatile LocationApp INSTANCE;
    private static final int DEFAULT_UPDATE_INTERVAL = 3;
    private static final int FAST_UPDATE_INTERVAL = 1;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    private float wayLatitude = 0.0f, wayLongitude = 0.0f;
    //Google API for location services. The majority of the app functions using this class
    private FusedLocationProviderClient fusedLocationProviderClient;
    //locatiom request its a config file
    private static LocationRequest locationRequest = new LocationRequest.Builder(0).setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setIntervalMillis(1000 * DEFAULT_UPDATE_INTERVAL)
            .setDurationMillis(15000)
            .setMinUpdateDistanceMeters(0)
            .build();


    //config file of location
        /*locationRequest = new LocationRequest.Builder(0).setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setIntervalMillis(1000 * DEFAULT_UPDATE_INTERVAL)
                .setDurationMillis(15000)
                .setMinUpdateDistanceMeters(20)
                .build();
*/


        /*locationRequest = LocationRequest.create()
                .setInterval(1000 * DEFAULT_UPDATE_INTERVAL)
                .setFastestInterval(1000 * FAST_UPDATE_INTERVAL)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(100);*/

    //used for update location
    //private LocationCallback locationCallBack;
    private Location location;
    private Context context;
    //εδώ πρέπει να γίνει livedata
    private float[] currentLocation = new float[2];



    private LocationApp(Context context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        Toast.makeText(context,
                "locationapp created ",Toast.LENGTH_SHORT).show();
        this.context = context;

    }

    public static LocationApp getInstance(Context context){
        if(INSTANCE == null) {
            synchronized (LocationApp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationApp(context);
                }
            }
        }
        return INSTANCE;

    }



    private final LocationCallback locationCallBack = new LocationCallback() {

        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult != null) {
                //εδώ πρέπει να το αλλάξω με το πάρε ένα location τελευταίο;
                setLocationData(locationResult.getLastLocation());

            }


        }
    };


    @Override
    protected void onActive() {
        super.onActive();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(context,
                    "PERMISSION not GRANTED ",Toast.LENGTH_SHORT).show();
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
               setLocationData(location);

                startLocationUpdates();
            }

        });

    }

    private float[] setLocationData(Location location) {
        if (location != null) {
            currentLocation[0] = (float) location.getLatitude();
            currentLocation[1] = (float) location.getLongitude();
           /* Toast.makeText(context,
                    "lat setlocation "+currentLocation[0],Toast.LENGTH_SHORT).show();*/
            setValue(currentLocation);
            return currentLocation;
        }
        return new float[]{0f, 0f};
    }
    public float getLatitude(){
        return (float) currentLocation[0];
    }

    public float getLongitude(){
        return (float) currentLocation[1];
    }

    public void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, Looper.getMainLooper());

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }
}
