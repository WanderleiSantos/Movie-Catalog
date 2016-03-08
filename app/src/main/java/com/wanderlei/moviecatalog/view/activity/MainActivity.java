package com.wanderlei.moviecatalog.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wanderlei.moviecatalog.MovieCatalogApplication;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.dagger.MainViewModule;
import com.wanderlei.moviecatalog.presenter.MainPresenter;
import com.wanderlei.moviecatalog.view.MainView;
import com.wanderlei.moviecatalog.view.fragment.MovieInTheatersFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {


    @Inject
    MainPresenter presenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((MovieCatalogApplication) getApplication()).getObjectGraph().plus(new MainViewModule(this)).inject(this);

        setSupportActionBar(toolbar);
        final ActionBar  actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager.setAdapter(new PagerAdapter());
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                case 0:
                    return new MovieInTheatersFragment();
                case 1:
                    return new MovieInTheatersFragment();
                case 2:
                    return new MovieInTheatersFragment();
                case 3:
                    return new MovieInTheatersFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                default:
                case 0:
                    return getString(R.string.lbl_intheaters);
                case 1:
                    return getString(R.string.lbl_popular);
                case 2:
                    return getString(R.string.lbl_upcoming);
                case 3:
                    return getString(R.string.lbl_categoria);
            }
        }
    }
}
