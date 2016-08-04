package com.wanderlei.moviecatalog.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.moviecatalog.model.api.GenericApi;
import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.ImageByCastAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.MoviesByPersonIdAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.PersonAsyncTask;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;
import com.wanderlei.moviecatalog.model.api.resources.ImageResource;

/**
 * Created by Wanderlei Santos on 29/07/2016.
 */
public class PersonApiImpl  extends GenericApi implements PersonApi {

    private CastResource castResource;
    private ImageResource imageResource;
    private PersonAsyncTask personAsyncTask;
    private MoviesByPersonIdAsyncTask moviesByPersonIdAsyncTask;
    private ImageByCastAsyncTask imageByCastAsyncTask;

    public PersonApiImpl(Context context, CastResource castResource, ImageResource imageResource) {
        super(context);
        this.castResource = castResource;
        this.imageResource = imageResource;
    }

    @Override
    public void findById(Long id) {
        verifyServiceResultListner();
        personAsyncTask = new PersonAsyncTask(getContext(), castResource);
        personAsyncTask.setApiResultListner(getServiceApiResultListner());
        personAsyncTask.execute(id);
    }

    @Override
    public void findMovies(Long id) {
        verifyServiceResultListner();
        moviesByPersonIdAsyncTask = new MoviesByPersonIdAsyncTask(getContext(), castResource);
        moviesByPersonIdAsyncTask.setApiResultListner(getServiceApiResultListner());
        moviesByPersonIdAsyncTask.execute(id);
    }

    @Override
    public void findGalery(Long id) {
        verifyServiceResultListner();
        imageByCastAsyncTask = new ImageByCastAsyncTask(getContext(), imageResource);
        imageByCastAsyncTask.setApiResultListner(getServiceApiResultListner());
        imageByCastAsyncTask.execute(id);
    }

    @Override
    public void cancelAllService() {
        if (personAsyncTask != null && personAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            personAsyncTask.cancel(true);
        }
    }
}
