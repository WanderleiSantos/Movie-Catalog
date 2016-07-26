package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.MovieResource;
import com.wanderlei.moviecatalog.model.entity.Movie;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Wanderlei Santos on 22/07/2016.
 */
public class MovieUpComingAsyncTask extends BaseAsyncTask<String, Void, List<Movie>> {

    private MovieResource movieResource;

    public MovieUpComingAsyncTask(Context context, MovieResource movieResource) {
        super(context);
        this.movieResource = movieResource;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(String... strings) {
       try{
           Response<List<Movie>> response = movieResource.getUpComing(getAPiKey(), 1).execute();
           switch (response.code()){
               case HTTP_OK:
                   return new AsyncTaskResult<>(response.body());
               default:
                   return new AsyncTaskResult<>(new BadRequestException());
           }

       } catch (IOException e){
           return new AsyncTaskResult<>(new BadRequestException());
       }
    }
}
