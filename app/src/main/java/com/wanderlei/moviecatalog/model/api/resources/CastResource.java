package com.wanderlei.moviecatalog.model.api.resources;

import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.model.entity.Person;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Wanderlei Santos on 21/07/2016.
 */
public interface CastResource {

    @GET("movie/{id}/credits")
    Call<List<Cast>> getMovieCredits(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("person/{id}")
    Call<Person> getPersonById(@Path("id") Long id, @Query("api_key") String apiKey);

    @GET("person/{id}/movie_credits")
    Call<List<Movie>> getMoviesByCast(@Path("id") Long id, @Query("api_key") String apiKey);


}
