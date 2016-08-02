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
 * Created by Wanderlei Santos on 01/08/2016.
 */
public class MovieListAsyncTask extends BaseAsyncTask<Long, Void, List<Movie>> {

    private MovieResource movieResource;

    public MovieListAsyncTask(Context context, MovieResource movieResource) {
        super(context);
        this.movieResource = movieResource;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(Long... longs) {
        try{
            Response<List<Movie>> response = movieResource.getMovieListByGenre(longs[0], getAPiKey(), 1).execute();
            switch (response.code()){
                case HTTP_OK:
                    return new AsyncTaskResult<>(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (IOException ex){
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
