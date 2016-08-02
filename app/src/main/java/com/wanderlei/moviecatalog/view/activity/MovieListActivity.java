package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.MovieListViewModule;
import com.wanderlei.moviecatalog.model.entity.Genre;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieListView;
import com.wanderlei.moviecatalog.view.adapter.MovieNowPlayingAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 01/08/2016.
 */
public class MovieListActivity extends AppCompatActivity implements MovieListView {

    private static final String INTENT_KEY_MOVIE = "intent_key_movie";
    private ActionBar mActionBar;
    private Movie mMovie;
    private Genre mGenre;
    private List<Movie> mMovieList;

    @Inject
    MoviePresenter presenter;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.recyclerview_movies)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmovie);
        ButterKnife.bind(this);

        ((MovieCatalogApplication) getApplication()).getObjectGraph().plus(new MovieListViewModule(this)).inject(this);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mGenre = getIntent().getParcelableExtra(INTENT_KEY_MOVIE);
        presenter.getListMovieByGenre(mGenre.getId().longValue());
    }

    public static Intent newIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, MovieListActivity.class);
        intent.putExtra(INTENT_KEY_MOVIE, genre);

        return intent;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mMovieList != null){
            outState.putParcelableArrayList(INTENT_KEY_MOVIE, new ArrayList<>(mMovieList));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
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
        Toast.makeText(this, "Erro ao carregar movies", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovies(List<Movie> movieList) {
        this.mMovieList = movieList;
        mActionBar.setTitle(mGenre.getName());
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new MovieNowPlayingAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie) {
                startActivity(MovieDetActicity.newIntent(MovieListActivity.this, movie));
            }
        }));

        GridLayoutManager lLayout = new GridLayoutManager(MovieListActivity.this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
    }
}
