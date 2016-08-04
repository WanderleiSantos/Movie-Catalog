package com.wanderlei.moviecatalog.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.api.ItemTypeAdapterFactory;
import com.wanderlei.moviecatalog.model.api.MovieApi;
import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.model.api.impl.MovieApiImpl;
import com.wanderlei.moviecatalog.model.api.impl.PersonApiImpl;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;
import com.wanderlei.moviecatalog.model.api.resources.GenreResource;
import com.wanderlei.moviecatalog.model.api.resources.ImageResource;
import com.wanderlei.moviecatalog.model.api.resources.MovieResource;
import com.wanderlei.moviecatalog.util.JsonDateDeserializer;

import java.util.Arrays;
import java.util.Date;

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
    public CastResource provideCastResource(Context context){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory("cast"))
                .registerTypeAdapter(Date.class, new JsonDateDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(CastResource.class);
    }

    @Provides
    public GenreResource provideGenreResource(Context context){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory("genres"))
                .setDateFormat("yyyy-MM-dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(GenreResource.class);
    }

    @Provides
    public ImageResource provideImageResource(Context context){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory(Arrays.asList("backdrops", "posters", "profiles")))
                .registerTypeAdapter(Date.class, new JsonDateDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ImageResource.class);
    }


    @Provides
    public MovieApi provideMovieApi(Context context){
        return new MovieApiImpl(context, provideMovieResource(context), provideCastResource(context), provideGenreResource(context));
    }

    @Provides
    public PersonApi provideApi(Context context){
        return new PersonApiImpl(context, provideCastResource(context), provideImageResource(context));
    }


}
