package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.CastResource;
import com.wanderlei.moviecatalog.model.entity.Cast;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by wanderlei on 15/03/16.
 */
public class MovieCreditsAsyncTask  extends BaseAsyncTask<Integer , Void, List<Cast>> {

    private CastResource castResource;

    public MovieCreditsAsyncTask(Context context, CastResource castResource) {
        super(context);
        this.castResource = castResource;
    }

    @Override
    protected AsyncTaskResult<List<Cast>> doInBackground(Integer... params) {
        try {

            Response<List<Cast>> listResponse = castResource.getMovieCredits(params[0].toString(), getAPiKey()).execute();
            switch (listResponse.code()) {
                case HTTP_OK:
                    return new AsyncTaskResult(listResponse.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (IOException ex) {
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
