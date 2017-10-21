package com.example.elham.myimdb.rest;


import com.example.elham.myimdb.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Elham on 9/20/2017.
 */
public interface ApiInterface {
    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}