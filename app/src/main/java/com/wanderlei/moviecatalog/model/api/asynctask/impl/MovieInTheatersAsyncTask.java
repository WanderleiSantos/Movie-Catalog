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
 * Created by wanderlei on 07/03/16.
 */
public class MovieInTheatersAsyncTask extends BaseAsyncTask<String , Void, List<Movie>> {

    private MovieResource movieResource;
    private String primary_release_date_gte;
    private String primary_release_date_lte;

    public MovieInTheatersAsyncTask(Context context, MovieResource movieResource, String primary_release_date_gte, String primary_release_date_lte) {
        super(context);
        this.movieResource = movieResource;
        this.primary_release_date_gte = primary_release_date_gte;
        this.primary_release_date_lte = primary_release_date_lte;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(String... params){
        try{

            Response<List<Movie>> listResponse = movieResource.getMovieInTheaters(getAPiKey(), primary_release_date_gte, primary_release_date_lte).execute();
            switch (listResponse.code()){
                case HTTP_OK:
                    return new AsyncTaskResult<>(listResponse.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (IOException ex){
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
