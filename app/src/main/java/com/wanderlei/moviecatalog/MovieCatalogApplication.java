package com.wanderlei.moviecatalog;

import android.app.Application;

import com.wanderlei.moviecatalog.dagger.ApiModule;
import com.wanderlei.moviecatalog.dagger.AppModule;

import dagger.ObjectGraph;

/**
 * Created by wanderlei on 04/03/16.
 */
public class MovieCatalogApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(
                new Object[]{
                        new AppModule(MovieCatalogApplication.this),
                        new ApiModule(),
                }
        );
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
