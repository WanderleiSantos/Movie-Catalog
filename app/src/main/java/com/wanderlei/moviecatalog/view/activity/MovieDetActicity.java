package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Movie;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 11/03/16.
 */
public class MovieDetActicity extends AppCompatActivity {

    private static final String INTENT_KEY_MOVIE = "intent_key_movie";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Movie movie = getIntent().getParcelableExtra(INTENT_KEY_MOVIE);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(movie.getTitle());


        if (movie.getPoster_path() != null) {
            Picasso.with(this)
                    .load(getString(R.string.base_url_img__logo185) + movie.getPoster_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(imageview_photo);

            Picasso.with(this)
                    .load(getString(R.string.base_url_img_logo500) + movie.getPoster_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(imageview_photoback);

        } else {
            imageview_photo.setImageDrawable(this.getDrawable(R.drawable.noimagemovie));
            imageview_photoback.setImageDrawable(this.getDrawable(R.drawable.noimagemovie));
        }

        text_view_description.setText(movie.getOverview());
        text_view_original_title.setText(movie.getOriginal_title());
       // text_view_critics.setText(movie.describeContents());
        if (movie.getRuntime() != null) {
            text_view_runtime.setText(movie.getRuntime());
        }


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
}
