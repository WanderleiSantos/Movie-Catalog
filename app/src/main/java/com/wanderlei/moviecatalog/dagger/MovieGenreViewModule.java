package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieGenreView;
import com.wanderlei.moviecatalog.view.fragment.MovieGenresFragmentView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Wanderlei Santos on 25/07/2016.
 */
@Module(injects = MovieGenresFragmentView.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class MovieGenreViewModule {

    private MovieGenreView movieGenreView;

    public MovieGenreViewModule(MovieGenreView movieGenreView) {
        this.movieGenreView = movieGenreView;
    }

    @Provides
    public MoviePresenter provideMovieGenre(MovieApi movieApi){
        return new MoviePresenter(movieGenreView, movieApi);
    }
}
