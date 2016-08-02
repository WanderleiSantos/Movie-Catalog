package com.wanderlei.moviecatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.MovieGenreViewModule;
import com.wanderlei.moviecatalog.model.entity.Genre;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieGenreView;
import com.wanderlei.moviecatalog.view.activity.MovieListActivity;
import com.wanderlei.moviecatalog.view.adapter.GenresAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 25/07/2016.
 */
public class MovieGenresFragmentView extends Fragment implements MovieGenreView {

    @Inject
    MoviePresenter presenter;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.recyclerview_categorias)
    RecyclerView recyclerView;

    private final String BUNDLE_KEY_GENRELIST = "bundle_key_genrelist";
    private List<Genre> genreList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_genres, container, false);
        ButterKnife.bind(this, view);

        ((MovieCatalogApplication) getActivity().getApplication()).getObjectGraph().plus(new MovieGenreViewModule(this)).inject(this);

        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(BUNDLE_KEY_GENRELIST) != null){
            genreList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_GENRELIST);
            showGenres(genreList);
        } else {
            presenter.loadGenres();
        }

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (genreList != null){
            outState.putParcelableArrayList(BUNDLE_KEY_GENRELIST, new ArrayList<>(genreList));
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
    public void NotLoadGenres() {
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Erro ao carregar generos", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showGenres(List<Genre> genreList) {
        this.genreList = genreList;
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new GenresAdapter(genreList, new OnItemClickListener<Genre>() {
            @Override
            public void onClick(Genre genre) {
                startActivity(MovieListActivity.newIntent(getActivity(), genre));
            }
        }));

        LinearLayoutManager lLayout = new LinearLayoutManager(getActivity());
        lLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
    }
}
