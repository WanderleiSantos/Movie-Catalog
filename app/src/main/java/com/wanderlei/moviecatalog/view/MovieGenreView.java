package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Genre;

import java.util.List;

/**
 * Created by Wanderlei Santos on 25/07/2016.
 */
public interface MovieGenreView {

    void showLoading();

    void closeLoading();

    void NotLoadGenres();

    void showGenres(List<Genre> genreList);

}
