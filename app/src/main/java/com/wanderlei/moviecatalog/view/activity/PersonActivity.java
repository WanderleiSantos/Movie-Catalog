package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.PersonViewModule;
import com.wanderlei.moviecatalog.model.entity.Cast;
import com.wanderlei.moviecatalog.model.entity.Person;
import com.wanderlei.moviecatalog.presenter.PersonPresenter;
import com.wanderlei.moviecatalog.view.PersonView;

import java.text.SimpleDateFormat;
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
    private ActionBar mActionBar;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        ButterKnife.bind(this);

        ((MovieCatalogApplication) getApplication()).getObjectGraph().plus(new PersonViewModule(this)).inject(this);

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);

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


    }
}
