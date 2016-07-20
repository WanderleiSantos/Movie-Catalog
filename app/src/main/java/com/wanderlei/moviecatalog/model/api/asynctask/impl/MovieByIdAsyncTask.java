package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.MovieResource;
import com.wanderlei.moviecatalog.model.entity.Movie;

import java.io.IOException;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by wanderlei on 15/03/16.
 */
public class MovieByIdAsyncTask extends BaseAsyncTask<Integer , Void, Movie> {

    private MovieResource movieResource;

    public MovieByIdAsyncTask(Context context, MovieResource movieResource) {
        super(context);
        this.movieResource = movieResource;
    }

    @Override
    protected AsyncTaskResult<Movie> doInBackground(Integer... params) {
        try {

            Response<Movie> listResponse = movieResource.getMovieById(params[0].toString(), getAPiKey()).execute();
            switch (listResponse.code()) {
                case HTTP_OK:
                    return new AsyncTaskResult(listResponse.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (IOException ex) {
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}