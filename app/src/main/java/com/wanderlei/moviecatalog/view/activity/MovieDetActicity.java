package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.MovieViewModule;
import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.presenter.MoviePresenter;
import com.wanderlei.moviecatalog.view.MovieView;
import com.wanderlei.moviecatalog.view.adapter.ActorsAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 11/03/16.
 */
public class MovieDetActicity extends AppCompatActivity implements MovieView {

    private static final String INTENT_KEY_MOVIE = "intent_key_movie";
    private ActionBar actionBar;

    private Integer movieId;

    private Movie mMovie;

    @Inject
    MoviePresenter presenter;

    @Bind(R.id.text_view_releasedate)
    TextView text_view_releasedate;

    @Bind(R.id.text_view_directedby)
    TextView text_view_directedby;

    @Bind(R.id.text_view_description)
    TextView text_view_description;

    @Bind(R.id.text_view_original_title)
    TextView text_view_original_title;

    @Bind(R.id.text_view_runtime)
    TextView text_view_runtime;

    @Bind(R.id.text_view_country)
    TextView text_view_country;

    @Bind(R.id.text_view_critics)
    TextView text_view_critics;

    @Bind(R.id.imageview_photoback)
    ImageView imageview_photoback;

    @Bind(R.id.imageview_photo)
    ImageView imageview_photo;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerview_actorsmovie)
    RecyclerView  recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        ((MovieCatalogApplication) getApplication()).getObjectGraph().plus(new MovieViewModule(this)).inject(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mMovie = getIntent().getParcelableExtra(INTENT_KEY_MOVIE);
        presenter.getMovieById(mMovie.getId());


    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetActicity.class);
        intent.putExtra(INTENT_KEY_MOVIE, movie);

        return intent;
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
    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void NotLoadMovies() {
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(this, "Erro ao carregar filme", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCasts(List<Cast> castList) {
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new ActorsAdapter(castList, new OnItemClickListener<Cast>(){
            @Override
            public void onClick(Cast cast) {
                //
            }
        }));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void showMovie(Movie movie) {

        this.mMovie = movie;
        actionBar.setTitle(mMovie.getTitle());

        if (mMovie.getPoster_path() != null) {
            Picasso.with(this)
                    .load(getString(R.string.base_url_img__logo185) + mMovie.getPoster_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(imageview_photo);

            Picasso.with(this)
                    .load(getString(R.string.base_url_img_logo500) + mMovie.getPoster_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(imageview_photoback);

        } else {
            imageview_photo.setImageDrawable(this.getDrawable(R.drawable.noimagemovie));
            imageview_photoback.setImageDrawable(this.getDrawable(R.drawable.noimagemovie));
        }

        text_view_description.setText(mMovie.getOverview());
        text_view_original_title.setText(mMovie.getOriginal_title());
        // text_view_critics.setText(movie.describeContents());
      //  if (mMovie.getRuntime() != null) {
    //        text_view_runtime.setText(mMovie.getRuntime());
     //   }

      //  presenter.getMovieCredits(movie.getId());

    }
}
