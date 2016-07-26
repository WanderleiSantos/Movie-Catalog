package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieNowPlayingView;
import com.wanderlei.moviecatalog.view.fragment.MovieNowPlayingFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 07/03/16.
 */
@Module(injects = MovieNowPlayingFragment.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class MovieNowPlayingViewModule {

    private MovieNowPlayingView view;

    public MovieNowPlayingViewModule(MovieNowPlayingView view) {
        this.view = view;
    }

    @Provides
    public MoviePresenter provideMovieInTheatersPresenter(MovieApi movieApi){
        return new MoviePresenter(view, movieApi);
    }
}
