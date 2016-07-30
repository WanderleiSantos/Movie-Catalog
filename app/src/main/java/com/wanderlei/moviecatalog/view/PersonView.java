package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.model.entity.Person;

import java.util.List;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
public interface PersonView {

    void showLoading();

    void closeLoading();

    void NotLoadPerson();

    void showPerson(Person person);

    void showMovies(List<Movie> movieList);
}
