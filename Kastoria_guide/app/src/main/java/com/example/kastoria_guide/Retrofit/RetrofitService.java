package com.example.kastoria_guide.Retrofit;



import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        //στο base url θα πρέπει να βάλεις την ip του sever και την πόρτα που συνδέεται ο server
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}

