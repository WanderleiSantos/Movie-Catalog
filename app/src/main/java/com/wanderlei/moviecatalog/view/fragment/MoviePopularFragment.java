package com.wanderlei.moviecatalog.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.view.MoviePopularView;

import java.util.List;

/**
 * Created by wanderlei on 24/06/16.
 */
public class MoviePopularFragment extends Fragment implements MoviePopularView , SwipeRefreshLayout.OnRefreshListener {

    @Override
    public void onRefresh() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void NotLoadMovies() {

    }

    @Override
    public void showMovies(List<Movie> movieList) {

    }
}
