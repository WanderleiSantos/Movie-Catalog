package com.wanderlei.moviecatalog.model.api.resources;

import com.wanderlei.moviecatalog.model.entity.Cast;

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

}
