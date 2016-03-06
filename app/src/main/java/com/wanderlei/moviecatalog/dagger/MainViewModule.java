package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.presenter.MainPresenter;
import com.wanderlei.moviecatalog.view.MainView;
import com.wanderlei.moviecatalog.view.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 05/03/16.
 */
@Module(injects = MainActivity.class, includes = {AppModule.class}, library = true)
public class MainViewModule {

    private MainView view;

    public MainViewModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainPresenter provideMainPresenter(){
        return new MainPresenter(view);
    }
}
