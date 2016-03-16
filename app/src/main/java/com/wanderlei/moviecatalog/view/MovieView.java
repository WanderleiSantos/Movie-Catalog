package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

/**
 * Created by wanderlei on 15/03/16.
 */
public interface MovieView {

    void showLoading();

    void closeLoading();

    void NotLoadMovies();

    void showCasts(List<Cast> castList);

    void showMovie(Movie movie);
}
