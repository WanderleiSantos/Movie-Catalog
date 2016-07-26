package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieUpComingView;
import com.wanderlei.moviecatalog.view.fragment.MovieUpComingFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Wanderlei Santos on 22/07/2016.
 */
@Module(injects = MovieUpComingFragment.class, includes = {ApiModule.class, AppModule.class}, library = true)
public class MovieUpComingViewModule {

    private MovieUpComingView upComingView;

    public MovieUpComingViewModule(MovieUpComingView upComingView) {
        this.upComingView = upComingView;
    }

    @Provides
    public MoviePresenter provideMovieUpComingPresenter(MovieApi movieApi){
        return new MoviePresenter(upComingView, movieApi);
    }
}
