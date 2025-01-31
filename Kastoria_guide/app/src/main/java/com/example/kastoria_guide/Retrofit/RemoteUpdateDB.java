package com.example.kastoria_guide.Retrofit;

import android.app.Application;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.Model.PlaceRepository;
import com.example.kastoria_guide.Model.Vote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteUpdateDB {
    private PlaceRepository mRepository;
    private Application application;
    private List<Place> Placelist;
    private Place place;

    public RemoteUpdateDB(Application application) {
        this.application=application;
        this.mRepository=PlaceRepository.getInstance(application);
    }

    public void loadPlaces() {

        RetrofitService retrofitService = new RetrofitService();
        PlaceApi placeApi = retrofitService.getRetrofit().create(PlaceApi.class);
        placeApi.getAllPlaces()
                .enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                        Placelist=response.body();
                        if (Placelist!=null){
                            mRepository.deleteAll();
                            for (Place place:Placelist) {
                                mRepository.insert(place);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Place>> call, Throwable t) {
                        Toast.makeText(application.getApplicationContext(), "Failed to load Places", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    public void loadPlacesFromSettings(ProgressBar bar, Button button){
        RetrofitService retrofitService = new RetrofitService();
        PlaceApi placeApi = retrofitService.getRetrofit().create(PlaceApi.class);
        placeApi.getAllPlaces()
                .enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                        Placelist=response.body();
                        if (Placelist!=null){
                            mRepository.deleteAll();
                            for (Place place:Placelist) {
                                mRepository.insert(place);
                            }
                            Toast.makeText(application.getApplicationContext(), "load Places", Toast.LENGTH_SHORT).show();

                        }
                        bar.setVisibility(View.GONE);
                        button.setBackgroundColor(0xFF6200EE);
                        button.setClickable(true);
                    }

                    @Override
                    public void onFailure(Call<List<Place>> call, Throwable t) {
                        Toast.makeText(application.getApplicationContext(), "Failed to load Places"+t.getCause(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                        button.setBackgroundColor(0xFF6200EE);
                        button.setClickable(true);
                    }
                });

    }


    public void postVote(ProgressBar bar, Button button, Vote vote){
        RetrofitService retrofitService = new RetrofitService();
        PlaceApi placeApi = retrofitService.getRetrofit().create(PlaceApi.class);
        placeApi.createPost(vote.getPlaceid(),vote)
                .enqueue(new Callback<Vote>() {
                    @Override
                    public void onResponse(Call<Vote> call, Response<Vote> response) {
                        if (response.body()!=null){
                            Toast.makeText(application.getApplicationContext(), "rate  save successful!"+String.valueOf(response.body()), Toast.LENGTH_SHORT).show();
                            loadPlacesFromSettings(bar, button);
                        }

                    }

                    @Override
                    public void onFailure(Call<Vote> call, Throwable t) {
                        Toast.makeText(application.getApplicationContext(), "Save failed!!!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                });

    }


}

