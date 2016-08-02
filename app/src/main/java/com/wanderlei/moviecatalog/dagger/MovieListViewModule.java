package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieListView;
import com.wanderlei.moviecatalog.view.activity.MovieListActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Wanderlei Santos on 01/08/2016.
 */
@Module(injects = MovieListActivity.class, library = true, includes = {AppModule.class, ApiModule.class})
public class MovieListViewModule {

    private MovieListView movieListView;

    public MovieListViewModule(MovieListView movieListView) {
        this.movieListView = movieListView;
    }

    @Provides
    public MoviePresenter provideMovieListPresenter(MovieApi movieApi){
        return new MoviePresenter(movieListView, movieApi);
    }
}
