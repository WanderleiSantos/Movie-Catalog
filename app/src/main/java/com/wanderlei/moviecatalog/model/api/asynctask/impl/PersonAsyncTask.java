package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;
import com.wanderlei.moviecatalog.model.entity.Person;

import java.io.IOException;

import retrofit.Response;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Wanderlei Santos on 29/07/2016.
 */
public class PersonAsyncTask extends BaseAsyncTask<Long, Void, Person> {

    private CastResource resource;

    public PersonAsyncTask(Context context, CastResource resource) {
        super(context);
        this.resource = resource;
    }

    @Override
    protected AsyncTaskResult<Person> doInBackground(Long... longs) {
        try{
            Response<Person> response = resource.getPersonById(longs[0], getAPiKey()).execute();
            switch (response.code()){
                case HTTP_OK:
                    return new AsyncTaskResult<>(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (IOException ex){
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
