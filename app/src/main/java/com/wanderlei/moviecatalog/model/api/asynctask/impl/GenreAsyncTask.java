package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.GenreResource;
import com.wanderlei.moviecatalog.model.entity.Genre;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Wanderlei Santos on 25/07/2016.
 */
public class GenreAsyncTask extends BaseAsyncTask<Integer, Void, Void> {

    private GenreResource genreResource;

    public GenreAsyncTask(Context context, GenreResource genreResource) {
        super(context);
        this.genreResource = genreResource;
    }

    @Override
    protected AsyncTaskResult<Void> doInBackground(Integer... integers) {
        try {
            Response<List<Genre>> response = genreResource.getGenres(getAPiKey()).execute();
            switch (response.code()){
                case HTTP_OK:
                    return new AsyncTaskResult(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (IOException e){
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
