package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MoviePopularView;
import com.wanderlei.moviecatalog.view.fragment.MoviePopularFragment;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 24/06/16.
 */
@Module(injects = MoviePopularFragment.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class MoviePopularViewModule {

    private MoviePopularView view;

    public MoviePopularViewModule(MoviePopularView view) {
        this.view = view;
    }

    @Provides
    public MoviePresenter provideMoviePopularPresenter(MovieApi movieApi){
        return new MoviePresenter(view, movieApi);
    }
}
