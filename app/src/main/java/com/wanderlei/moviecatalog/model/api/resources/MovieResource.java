package com.wanderlei.moviecatalog.model.api.resources;

import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by wanderlei on 04/03/16.
 */
public interface MovieResource {

    @GET("/discover/movie") // Formato da data YYYY-MM-DD
    Call<List<Movie>> getMovieInTheaters(@Query("api_key") String api_key, @Query("primary_release_date.gte") String primary_release_date_gte,
                                         @Query("primary_release_date.lte") String primary_release_date_lte);

}
