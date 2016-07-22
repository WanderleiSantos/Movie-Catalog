package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

/**
 * Created by Wanderlei Santos on 20/07/2016.
 */
public interface MovieUpComingView {
    void showLoading();

    void closeLoading();

    void NotLoadMovies();

    void showMovies(List<Movie> movieList);
}
