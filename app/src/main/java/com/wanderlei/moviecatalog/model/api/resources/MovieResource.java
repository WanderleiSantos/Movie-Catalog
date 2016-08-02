package com.wanderlei.moviecatalog.model.api.resources;

import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by wanderlei on 04/03/16.
 */
public interface MovieResource {

    @GET("movie/now_playing")
    Call<List<Movie>> getNowPlaying(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/{id}")
    Call<Movie> getMovieById(@Path("id") String id, @Query("api_key") String api_key);

    @GET("movie/popular")
    Call<List<Movie>> getPopular(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/upcoming")
    Call<List<Movie>> getUpComing(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/top_rated")
    Call<List<Movie>> getTopRated(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("genre/{id}/movies")
    Call<List<Movie>> getMovieListByGenre(@Path("id") Long genreId, @Query("api_key") String apiKey, @Query("page") Integer page);

}
