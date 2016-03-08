package com.wanderlei.moviecatalog.presenter;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.asynctask.ApiResultListner;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.view.MovieInTheatersView;

import java.util.List;

/**
 * Created by wanderlei on 07/03/16.
 */
public class MovieInTheatersPresenter {

    private MovieInTheatersView view;
    private MovieApi api;

    public MovieInTheatersPresenter(MovieInTheatersView view, MovieApi api) {
        this.view = view;
        this.api = api;
    }

    public void loadMoviesInTheaters(String primary_release_date_gte, String primary_release_date_lte){
        view.showLoading();
        api.serServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                view.showMovies((List<Movie>) object);
                view.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                view.closeLoading();
                view.NotLoadMovies();
            }
        });

        api.getInTheaters(primary_release_date_gte, primary_release_date_lte);
    }
}
