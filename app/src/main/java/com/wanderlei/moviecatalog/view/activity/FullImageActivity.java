package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Image;
import com.wanderlei.moviecatalog.model.entity.Person;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 04/08/2016.
 */
public class FullImageActivity extends AppCompatActivity {

    ScaleGestureDetector scaleGestureDetector;
    Matrix matrix;


    private static final String BUNDLE_KEY_IMAGE = "bundle_key_image";
    private static final String BUNDLE_KEY_PERSON = "bundle_key_person";
    private Image image;
    private Person person;
    private ActionBar mActionBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.imageview_photo)
    ImageView imageView;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage);
        ButterKnife.bind(this);

        person = getIntent().getParcelableExtra(BUNDLE_KEY_PERSON);
        image = getIntent().getParcelableExtra(BUNDLE_KEY_IMAGE);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(person.getName());

        if (image.getFile_path() != null){
            progressBar.setVisibility(View.VISIBLE);

            Picasso.with(this)
                    .load(this.getString(R.string.base_url_img_logo500) + image.getFile_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            progressBar.setVisibility(View.GONE);
                        }
                    });

        } else {
            imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.noimagemovie));
        }

        //matrix = new Matrix();
        //scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//        return true;
//    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float sf = detector.getScaleFactor();
            sf = Math.max(0.1f, Math.min(sf, 0.5f));
            matrix.setScale(sf, sf);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }

    public static Intent newIntent(Context context, Image image, Person person){
        Intent intent = new Intent(context, FullImageActivity.class);
        intent.putExtra(BUNDLE_KEY_IMAGE, image);
        intent.putExtra(BUNDLE_KEY_PERSON, person);
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
