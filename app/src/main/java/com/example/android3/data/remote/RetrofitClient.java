package com.example.android3.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit provideRetrofit = new Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public FilmApi provideFilmApi() {
        return provideRetrofit.create(FilmApi.class);
    }
}
