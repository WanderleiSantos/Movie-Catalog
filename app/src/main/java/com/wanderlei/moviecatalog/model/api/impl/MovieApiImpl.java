package com.wanderlei.moviecatalog.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.moviecatalog.model.api.GenericApi;
import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MovieByIdAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MovieInTheatersAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MoviePopularAsyncTask;
import com.wanderlei.moviecatalog.model.api.resources.MovieResource;

/**
 * Created by wanderlei on 07/03/16.
 */
public class MovieApiImpl extends GenericApi implements MovieApi {

    private MovieResource movieResource;
    private MovieInTheatersAsyncTask movieInTheatersAsyncTask;
    private MoviePopularAsyncTask moviePopularAsyncTask;
    private MovieByIdAsyncTask movieByIdAsyncTask;


    public MovieApiImpl(Context context, MovieResource movieResource) {
        super(context);
        this.movieResource = movieResource;
    }

    @Override
    public void getInTheaters(String primary_release_date_gte, String primary_release_date_lte) {
        verifyServiceResultListner();
        movieInTheatersAsyncTask = new MovieInTheatersAsyncTask(getContext(), movieResource, primary_release_date_gte, primary_release_date_lte);
        movieInTheatersAsyncTask.setApiResultListner(getServiceApiResultListner());
        movieInTheatersAsyncTask.execute();

    }

    @Override
    public void getMovieCredits(Integer id) {

    }

    @Override
    public void getMovieById(Integer id) {
        verifyServiceResultListner();
        movieByIdAsyncTask = new MovieByIdAsyncTask(getContext(), movieResource);
        movieByIdAsyncTask.setApiResultListner(getServiceApiResultListner());
        movieByIdAsyncTask.execute(id);
    }

    @Override
    public void getPopular() {
        verifyServiceResultListner();
        moviePopularAsyncTask = new MoviePopularAsyncTask(getContext(), movieResource);
        moviePopularAsyncTask.setApiResultListner(getServiceApiResultListner());
        moviePopularAsyncTask.execute();
    }

    @Override
    public void getUpComing() {

    }

    @Override
    public void cancelAllService() {
        if(movieInTheatersAsyncTask != null && movieInTheatersAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            movieInTheatersAsyncTask.cancel(true);
        }
    }
}
