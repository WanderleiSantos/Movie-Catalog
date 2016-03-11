package com.wanderlei.moviecatalog.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.MovieInTheatersViewModule;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.presenter.MovieInTheatersPresenter;
import com.wanderlei.moviecatalog.view.MovieInTheatersView;
import com.wanderlei.moviecatalog.view.adapter.MovieInTheatersAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 07/03/16.
 */
public class MovieInTheatersFragment extends Fragment implements MovieInTheatersView,  SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MovieInTheatersPresenter presenter;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.recyclerview_moviesintheaters)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_movieintheaters)
    SwipeRefreshLayout swipe_movieintheaters;

    private final String BUNDLE_KEY_MOVIELIST = "bundle_key_movielist";
    private final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<Movie> movieList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_intheaters, container, false);
        ButterKnife.bind(this, view);
        ((MovieCatalogApplication) getActivity().getApplication()).getObjectGraph().plus(new MovieInTheatersViewModule(this)).inject(this);

        swipe_movieintheaters.setOnRefreshListener(this);

        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST) != null){
            movieList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST);
            showMovies(movieList);
        } else {
            presenter.loadMoviesInTheaters(simpleFormat.format(getDataAtual()), simpleFormat.format(getProximaSemana()));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (movieList != null){
            outState.putParcelableArrayList(BUNDLE_KEY_MOVIELIST, new ArrayList<>(movieList));
        }
        super.onSaveInstanceState(outState);
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

        if (swipe_movieintheaters.isRefreshing()) {
            swipe_movieintheaters.setRefreshing(false);
        }

        this.movieList = movieList;
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new MovieInTheatersAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie) {
                /////
            }
        }));

        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
    }

    @Override
    public void onRefresh() {
        recyclerView.setVisibility(View.GONE);
        presenter.loadMoviesInTheaters(simpleFormat.format(getDataAtual()), simpleFormat.format(getProximaSemana()));
        progressBar.setVisibility(View.INVISIBLE);
    }

    public Date getDataAtual() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.getTime();
    }

    public Date getProximaSemana(){
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH) + 7;
        calendar.set(ano, mes, dia);
        return calendar.getTime();
    }
}
