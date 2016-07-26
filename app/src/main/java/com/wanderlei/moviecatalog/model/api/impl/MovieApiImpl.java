package com.wanderlei.moviecatalog.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.moviecatalog.model.api.GenericApi;
import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.GenreAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MovieByIdAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MovieCreditsAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MovieNowPlayingAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MoviePopularAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MovieUpComingAsyncTask;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;
import com.wanderlei.moviecatalog.model.api.resources.GenreResource;
import com.wanderlei.moviecatalog.model.api.resources.MovieResource;

/**
 * Created by wanderlei on 07/03/16.
 */
public class MovieApiImpl extends GenericApi implements MovieApi {

    private MovieResource movieResource;
    private CastResource castResource;
    private GenreResource genreResource;
    private MovieNowPlayingAsyncTask movieNowPlayingAsyncTask;
    private MoviePopularAsyncTask moviePopularAsyncTask;
    private MovieByIdAsyncTask movieByIdAsyncTask;
    private MovieCreditsAsyncTask movieCreditsAsyncTask;
    private MovieUpComingAsyncTask movieUpComingAsyncTask;
    private GenreAsyncTask genreAsyncTask;

    public MovieApiImpl(Context context, MovieResource movieResource, CastResource castResource, GenreResource genreResource) {
        super(context);
        this.movieResource = movieResource;
        this.castResource = castResource;
        this.genreResource = genreResource;
    }

    @Override
    public void getNowPlaying() {
        verifyServiceResultListner();
        movieNowPlayingAsyncTask = new MovieNowPlayingAsyncTask(getContext(), movieResource);
        movieNowPlayingAsyncTask.setApiResultListner(getServiceApiResultListner());
        movieNowPlayingAsyncTask.execute();

    }

    @Override
    public void getMovieCredits(Integer id) {
        verifyServiceResultListner();
        movieCreditsAsyncTask = new MovieCreditsAsyncTask(getContext(), castResource);
        movieCreditsAsyncTask.setApiResultListner(getServiceApiResultListner());
        movieCreditsAsyncTask.execute(id);
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
        verifyServiceResultListner();
        movieUpComingAsyncTask = new MovieUpComingAsyncTask(getContext(), movieResource);
        movieUpComingAsyncTask.setApiResultListner(getServiceApiResultListner());
        movieUpComingAsyncTask.execute();
    }

    @Override
    public void getGenres() {
        verifyServiceResultListner();
        genreAsyncTask = new GenreAsyncTask(getContext(), genreResource);
        genreAsyncTask.setApiResultListner(getServiceApiResultListner());
        genreAsyncTask.execute();
    }

    @Override
    public void cancelAllService() {
        if(movieNowPlayingAsyncTask != null && movieNowPlayingAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            movieNowPlayingAsyncTask.cancel(true);
        }
    }
}
