package com.example.kastoria_guide.Retrofit;
/*
{"id":23,"email":"haris2@gmail.com","rating":5,"placeid":66}
http://localhost:8080/places/66/Vote
Για να δείς για το retrofit και τις λέξεις κλειδία όπως get,post,path κτλ
https://square.github.io/retrofit/
 */
import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.Model.Vote;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlaceApi {

    @GET("/place/get-all")
    Call<List<Place>> getAllPlaces();

    @GET("/place/{placeid}")
    Call <Place> getPlace(@Path("placeid") int placeid);


    @POST("/place/save")
    Call<Place> save (@Body Place place);

    //save a vote
    @POST("/places/{placeid}/Vote")
    Call<Vote> createPost(@Path("placeid") int placeid,@Body Vote datavote);
}
