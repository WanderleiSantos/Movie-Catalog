package com.wanderlei.moviecatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Image;
import com.wanderlei.moviecatalog.model.entity.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 04/08/2016.
 */
public class FullImageActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY_IMAGE = "bundle_key_image";
    private static final String BUNDLE_KEY_PERSON = "bundle_key_person";
    private static final String BUNDLE_KEY_POSITION = "BUNDLE_KEY_POSITION";
    private int mPosition;
    private List<Image> mImageList;

    private Person mPerson;
    private ActionBar mActionBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimageslider);
        ButterKnife.bind(this);

        mPerson  = getIntent().getParcelableExtra(BUNDLE_KEY_PERSON);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(mPerson.getName());

        mImageList = getIntent().getParcelableArrayListExtra(BUNDLE_KEY_IMAGE);
        mPosition = getIntent().getIntExtra(BUNDLE_KEY_POSITION, 0);

        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return mImageList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(FullImageActivity.this).inflate(R.layout.activity_fullimage, container, false);
                ImageView imageView = (ImageView) view.findViewById(R.id.imageview_photo);
                final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
                Picasso.with(FullImageActivity.this)
                        .load(getString(R.string.base_url_img_logo500) + mImageList.get(position).getFile_path())
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

                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        viewPager.setCurrentItem(mPosition);

    }


    public static Intent newIntent(Context context, ArrayList<Image> imageList, int position, Person person) {
        Intent intent = new Intent(context, FullImageActivity.class);
        intent.putParcelableArrayListExtra(BUNDLE_KEY_IMAGE, imageList);
        intent.putExtra(BUNDLE_KEY_POSITION, position);
        intent.putExtra(BUNDLE_KEY_PERSON, person);

        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
