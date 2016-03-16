package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieInTheatersView;
import com.wanderlei.moviecatalog.view.fragment.MovieInTheatersFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 07/03/16.
 */
@Module(injects = MovieInTheatersFragment.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class MovieInTheatersViewModule {

    private MovieInTheatersView view;

    public MovieInTheatersViewModule(MovieInTheatersView view) {
        this.view = view;
    }

    @Provides
    public MoviePresenter provideMovieInTheatersPresenter(MovieApi movieApi){
        return new MoviePresenter(view, movieApi);
    }
}
