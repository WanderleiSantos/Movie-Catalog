package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.wanderlei.moviecatalog.dagger.PersonViewModule;
import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Image;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.model.entity.Person;
import com.wanderlei.moviecatalog.presenter.PersonPresenter;
import com.wanderlei.moviecatalog.view.PersonView;
import com.wanderlei.moviecatalog.view.adapter.ImageAdapter;
import com.wanderlei.moviecatalog.view.adapter.MoviesAdapter;
import com.wanderlei.moviecatalog.view.adapter.OnItemClickListener;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
public class PersonActivity extends AppCompatActivity implements PersonView {

    private static final String BUNDLE_KEY_PERSON = "bundle_key_person";

    private Person mPerson;
    private Cast mCast;
    private ActionBar mActionBar;
    private Long mId;

    @Inject
    PersonPresenter mPersonPresenter;

    @Bind(R.id.imageview_photo)
    ImageView mImageView;

    @Bind(R.id.text_view_age)
    TextView mTextViewAge;

    @Bind(R.id.text_view_born)
    TextView mTextViewBorn;

    @Bind(R.id.text_view_description)
    TextView mTextViewDesc;

    @Bind(R.id.recyclerview_actor)
    RecyclerView mRecyclerViewActor;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recyclerview_galery)
    RecyclerView mRecyclerViewGalery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        ButterKnife.bind(this);

        ((MovieCatalogApplication) getApplication()).getObjectGraph().plus(new PersonViewModule(this)).inject(this);

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mCast = getIntent().getParcelableExtra(BUNDLE_KEY_PERSON);
        mPersonPresenter.getPersonById( mCast.getId().longValue());
        mPersonPresenter.getMovies(mCast.getId().longValue());
        mPersonPresenter.getGalery(mCast.getId().longValue());

    }

    public static Intent newIntent(Context context, Cast cast){
        Intent intent = new Intent(context, PersonActivity.class);
        intent.putExtra(BUNDLE_KEY_PERSON, cast);
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
        mRecyclerViewActor.setVisibility(View.GONE);
    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void NotLoadPerson() {
        mRecyclerViewActor.setVisibility(View.GONE);
        Toast.makeText(this, "Erro Carregar Ator", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPerson(Person person) {
        this.mPerson = person;
        mActionBar.setTitle(mPerson.getName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        mTextViewAge.setText(simpleDateFormat.format(mPerson.getBirthday()));
        mTextViewBorn.setText(mPerson.getPlaceOfBirth());
        mTextViewDesc.setText(mPerson.getBiography());

        if (mPerson.getProfilePath() != null) {
            Picasso.with(this)
                    .load(getString(R.string.base_url_img_logo500) + mPerson.getProfilePath())
                    .placeholder(R.drawable.noimagemovie)
                    .into(mImageView);

        } else {
            mImageView.setImageDrawable(this.getDrawable(R.drawable.noimagemovie));
        }

    }

    @Override
    public void showMovies(List<Movie> movieList) {
        mRecyclerViewActor.setVisibility(View.VISIBLE);
        mRecyclerViewActor.setAdapter(new MoviesAdapter(movieList, new OnItemClickListener<Movie>(){
            @Override
            public void onClick(Movie movie) {
                startActivity(MovieDetActicity.newIntent(PersonActivity.this, movie));
            }
        }));

        mRecyclerViewActor.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerViewActor.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showImages(List<Image> imageList) {
        mRecyclerViewGalery.setVisibility(View.VISIBLE);
        mRecyclerViewGalery.setAdapter(new ImageAdapter(imageList, new OnItemClickListener<Image>() {
            @Override
            public void onClick(Image image) {
                //
            }
        }));

        mRecyclerViewGalery.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerViewGalery.setItemAnimator(new DefaultItemAnimator());
    }
}
