package com.wanderlei.moviecatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.MovieUpComingViewModule;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieUpComingView;
import com.wanderlei.moviecatalog.view.activity.MovieDetActicity;
import com.wanderlei.moviecatalog.view.adapter.MovieUpComingAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 22/07/2016.
 */
public class MovieUpComingFragment extends Fragment implements MovieUpComingView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MoviePresenter moviePresenter;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.recyclerview_moviesupcoming)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_moviesupcoming)
    SwipeRefreshLayout swipe_moviesupcoming;

    private final String BUNDLE_KEY_MOVIELIST = "bundle_key_movielist";
    private List<Movie> movieList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_upcoming, container, false);
        ButterKnife.bind(this, view);

        ((MovieCatalogApplication) getActivity().getApplication()).getObjectGraph().plus(new MovieUpComingViewModule(this)).inject(this);

        swipe_moviesupcoming.setOnRefreshListener(this);

        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST) != null){
            movieList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST);
            showMovies(movieList);
        } else {
            moviePresenter.loadMoviesUpComing();
        }

        return view;
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void NotLoadMovies() {
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Erro ao carregar filmes", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovies(List<Movie> movieList) {
        if (swipe_moviesupcoming.isRefreshing()){
            swipe_moviesupcoming.setRefreshing(false);
        }

        this.movieList = movieList;
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new MovieUpComingAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie) {
                startActivity(MovieDetActicity.newIntent(getActivity(), movie));
            }
        }));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onRefresh() {
        recyclerView.setVisibility(View.GONE);
        moviePresenter.loadMoviesUpComing();
        recyclerView.setVisibility(View.VISIBLE);
    }
}
