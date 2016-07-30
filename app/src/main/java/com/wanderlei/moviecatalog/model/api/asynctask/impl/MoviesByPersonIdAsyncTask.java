package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;
import com.wanderlei.moviecatalog.model.entity.Movie;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Wanderlei Santos on 29/07/2016.
 */
public class MoviesByPersonIdAsyncTask extends BaseAsyncTask<Long, Void, List<Movie>> {

    private CastResource resource;

    public MoviesByPersonIdAsyncTask(Context context, CastResource resource) {
        super(context);
        this.resource = resource;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(Long... longs) {
        try{
            Response<List<Movie>> response = resource.getMoviesByCast(longs[0], getAPiKey()).execute();
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
