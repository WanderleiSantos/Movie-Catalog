package com.wanderlei.moviecatalog.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 04/03/16.
 */
@Module(library = true)
public class AppModule {

    private static Application application;

    public AppModule() {
    }

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    public Application provideApplication(){
        return application;
    }

    @Provides
    public Context provideContext(){
        return application;
    }


}
