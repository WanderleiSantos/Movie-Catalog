package com.wanderlei.moviecatalog.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.moviecatalog.model.api.GenericApi;
import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.model.api.asynctask.impl.PersonAsyncTask;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;

/**
 * Created by Wanderlei Santos on 29/07/2016.
 */
public class PersonApiImpl  extends GenericApi implements PersonApi {

    private CastResource castResource;
    private PersonAsyncTask personAsyncTask;

    public PersonApiImpl(Context context, CastResource castResource) {
        super(context);
        this.castResource = castResource;
    }

    @Override
    public void findById(Long id) {
        verifyServiceResultListner();
        personAsyncTask = new PersonAsyncTask(getContext(), castResource);
        personAsyncTask.setApiResultListner(getServiceApiResultListner());
        personAsyncTask.execute(id);
    }

    @Override
    public void cancelAllService() {
        if (personAsyncTask != null && personAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            personAsyncTask.cancel(true);
        }
    }
}
