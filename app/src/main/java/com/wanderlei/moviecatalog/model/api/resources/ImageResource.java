package com.wanderlei.moviecatalog.model.api.resources;

import com.wanderlei.moviecatalog.model.entity.Image;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Wanderlei Santos on 02/08/2016.
 */
public interface ImageResource {

    @GET("person/{id}/images")
    Call<List<Image>> getListByPerson(@Path("id") Long  id, @Query("api_key") String apiKey);
}
