package com.wanderlei.moviecatalog.presenter;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.asynctask.ApiResultListner;
import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.view.MovieInTheatersView;
import com.wanderlei.moviecatalog.view.MovieView;

import java.util.List;

/**
 * Created by wanderlei on 07/03/16.
 */
public class MoviePresenter {

    private MovieInTheatersView movieInTheatersView;
    private MovieView movieView;
    private MovieApi api;

    public MoviePresenter(MovieInTheatersView view, MovieApi api) {
        this.movieInTheatersView = view;
        this.api = api;
    }

    public MoviePresenter(MovieView movieView, MovieApi api) {
        this.movieView = movieView;
        this.api = api;
    }

    public void loadMoviesInTheaters(String primary_release_date_gte, String primary_release_date_lte){
        movieInTheatersView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieInTheatersView.showMovies((List<Movie>) object);
                movieInTheatersView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieInTheatersView.closeLoading();
                movieInTheatersView.NotLoadMovies();
            }
        });

        api.getInTheaters(primary_release_date_gte, primary_release_date_lte);
    }

    public void getMovieById(Integer id){
        movieView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieView.showMovie((Movie) object);
                movieView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieView.closeLoading();
                movieView.NotLoadMovies();
            }
        });

        api.getMovieById(id);
    }

    public void getMovieCredits(Integer id){
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieView.showCasts((List<Cast>) object);
            }

            @Override
            public void onExecption(Exception exception) {

            }
        });

        api.getMovieById(id);
    }
}
