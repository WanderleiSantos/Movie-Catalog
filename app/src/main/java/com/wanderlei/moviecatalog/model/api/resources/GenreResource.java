package com.wanderlei.moviecatalog.model.api.resources;

import com.wanderlei.moviecatalog.model.entity.Genre;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Wanderlei Santos on 22/07/2016.
 */
public interface GenreResource {

    @GET("genre/movie/list")
    Call<List<Genre>> getGenres(@Query("api_key") String apiKey);
}
