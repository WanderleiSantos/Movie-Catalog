package com.wanderlei.moviecatalog.presenter;

import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.asynctask.ApiResultListner;
import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Genre;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.view.MovieGenreView;
import com.wanderlei.moviecatalog.view.MovieListView;
import com.wanderlei.moviecatalog.view.MovieNowPlayingView;
import com.wanderlei.moviecatalog.view.MoviePopularView;
import com.wanderlei.moviecatalog.view.MovieUpComingView;
import com.wanderlei.moviecatalog.view.MovieView;

import java.util.List;

/**
 * Created by wanderlei on 07/03/16.
 */
public class MoviePresenter {

    private MovieNowPlayingView movieNowPlayingView;
    private MoviePopularView  moviePopularView;
    private MovieView movieView;
    private MovieUpComingView movieUpComingView;
    private MovieApi api;
    private MovieGenreView movieGenreView;
    private MovieListView movieListView;

    public MoviePresenter( MovieListView movieListView, MovieApi api) {
        this.api = api;
        this.movieListView = movieListView;
    }

    public MoviePresenter(MovieGenreView movieGenreView, MovieApi api) {
        this.api = api;
        this.movieGenreView = movieGenreView;
    }

    public MoviePresenter(MovieNowPlayingView view, MovieApi api) {
        this.movieNowPlayingView = view;
        this.api = api;
    }

    public MoviePresenter(MoviePopularView view, MovieApi api) {
        this.moviePopularView = view;
        this.api = api;
    }

    public MoviePresenter(MovieUpComingView view, MovieApi api){
        this.movieUpComingView = view;
        this.api = api;
    }

    public MoviePresenter(MovieView movieView, MovieApi api) {
        this.movieView = movieView;
        this.api = api;
    }

    public void loadMoviesInTheaters(){
        movieNowPlayingView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieNowPlayingView.showMovies((List<Movie>) object);
                movieNowPlayingView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieNowPlayingView.closeLoading();
                movieNowPlayingView.NotLoadMovies();
            }
        });

        api.getNowPlaying();
    }

    public void loadMoviesUpComing(){
        movieUpComingView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieUpComingView.showMovies((List<Movie>) object);
                movieUpComingView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieUpComingView.closeLoading();
                movieUpComingView.NotLoadMovies();
            }
        });

        api.getUpComing();
    }

    public void loadGenres(){
        movieGenreView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieGenreView.showGenres((List<Genre>) object);
                movieGenreView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieGenreView.closeLoading();
                movieGenreView.NotLoadGenres();
            }
        });

        api.getGenres();
    }

    public void loadMoviesPopular(){
        moviePopularView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                moviePopularView.showMovies((List<Movie>) object);
                moviePopularView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                moviePopularView.closeLoading();
                moviePopularView.NotLoadMovies();
            }
        });
        api.getPopular();
    }

    public void getMovieById(Integer id){
        movieView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieView.showMovie((Movie) object);
                movieView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieView.closeLoading();
                movieView.NotLoadMovies();
            }
        });

        api.getMovieById(id);
    }

    public void getMovieCredits(Integer id){
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieView.showCasts((List<Cast>) object);
            }

            @Override
            public void onExecption(Exception exception) {
            }
        });
        api.getMovieCredits(id);
    }

    public void getListMovieByGenre(Long id){
        movieListView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                movieListView.showMovies((List<Movie>) object);
                movieListView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                movieListView.closeLoading();
                movieListView.NotLoadMovies();
            }
        });
        api.getListMovieByGenre(id);
    }
}
