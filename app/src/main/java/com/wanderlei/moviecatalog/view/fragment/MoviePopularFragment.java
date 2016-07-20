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
import com.wanderlei.moviecatalog.dagger.MoviePopularViewModule;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MoviePopularView;
import com.wanderlei.moviecatalog.view.activity.MovieDetActicity;
import com.wanderlei.moviecatalog.view.adapter.MoviePopularAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 24/06/16.
 */
public class MoviePopularFragment extends Fragment implements MoviePopularView , SwipeRefreshLayout.OnRefreshListener {


    @Inject
    MoviePresenter presenter;


    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.recyclerview_moviepopular)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_moviepopular)
    SwipeRefreshLayout swipe_moviepopular;

    private final String BUNDLE_KEY_MOVIELIST = "bundle_key_movielist";
    private List<Movie> movieList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_popular, container, false);
        ButterKnife.bind(this, view);

        ((MovieCatalogApplication) getActivity().getApplication()).getObjectGraph().plus(new MoviePopularViewModule(this)).inject(this);

        swipe_moviepopular.setOnRefreshListener(this);

        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST) != null){
            movieList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST);
            showMovies(movieList);
        } else {
            presenter.loadMoviesPopular();
        }

        return view;
    }

    @Override
    public void onRefresh() {
        recyclerView.setVisibility(View.GONE);
        presenter.loadMoviesPopular();
        progressBar.setVisibility(View.INVISIBLE);
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
        if (swipe_moviepopular.isRefreshing()) {
            swipe_moviepopular.setRefreshing(false);
        }

        this.movieList = movieList;
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new MoviePopularAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie) {
                startActivity(MovieDetActicity.newIntent(getActivity(), movie));
            }
        }));

        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
    }
}
