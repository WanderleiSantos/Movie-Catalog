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
 * Created by wanderlei on 24/06/16.
 */
public class MoviePopularAsyncTask extends BaseAsyncTask<String , Void, List<Movie>> {

        private MovieResource movieResource;

        public MoviePopularAsyncTask(Context context, MovieResource movieResource) {
                super(context);
                this.movieResource = movieResource;
        }

        @Override
        protected AsyncTaskResult<List<Movie>> doInBackground(String... params) {
            try {

                Response<List<Movie>> listResponse = movieResource.getPopular(getAPiKey(), 1).execute();
                switch (listResponse.code()) {
                    case HTTP_OK:
                        return new AsyncTaskResult<>(listResponse.body());
                    default:
                        return new AsyncTaskResult<>(new BadRequestException());
                }
            } catch (IOException ex) {
                return new AsyncTaskResult<>(new BadRequestException());
            }
        }
}