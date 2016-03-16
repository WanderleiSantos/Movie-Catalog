package com.wanderlei.moviecatalog.dagger;

/**
 * Created by wanderlei on 15/03/16.
 */

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieView;
import com.wanderlei.moviecatalog.view.activity.MovieDetActicity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = MovieDetActicity.class, includes = {AppModule.class, ApiModule.class})
public class MovieViewModule {

    private MovieView view;

    public MovieViewModule(MovieView view) {
        this.view = view;
    }

    @Provides
    public MoviePresenter provideMoviePresenter(MovieApi movieApi){
        return new MoviePresenter(view, movieApi);
    }

}
