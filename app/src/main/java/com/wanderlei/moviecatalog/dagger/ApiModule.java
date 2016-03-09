package com.wanderlei.moviecatalog.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.api.ItemTypeAdapterFactory;
import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.impl.MovieApiImpl;
import com.wanderlei.moviecatalog.model.api.resources.MovieResource;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by wanderlei on 04/03/16.
 */
@Module(library = true, includes = AppModule.class)
public class ApiModule {

    @Provides
    public Retrofit provideRetrofit(Context context){
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setDateFormat("yyyy-MM-dd")
                .create();
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    public MovieResource provideMovieResource(Context context){
        return provideRetrofit(context).create(MovieResource.class);
    }

    @Provides
    public MovieApi provideMovieApi(Context context){
        return new MovieApiImpl(context, provideMovieResource(context));
    }


}
