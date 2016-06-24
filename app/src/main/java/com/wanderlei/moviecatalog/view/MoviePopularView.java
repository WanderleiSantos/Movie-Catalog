package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

/**
 * Created by wanderlei on 24/06/16.
 */
public interface MoviePopularView {

    void showLoading();

    void closeLoading();

    void NotLoadMovies();

    void showMovies(List<Movie> movieList);
}
