package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

/**
 * Created by wanderlei on 07/03/16.
 */
public interface MovieNowPlayingView {

    void showLoading();

    void closeLoading();

    void NotLoadMovies();

    void showMovies(List<Movie> movieList);
}
