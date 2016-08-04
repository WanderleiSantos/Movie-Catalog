package com.wanderlei.moviecatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.moviecatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.moviecatalog.model.api.asynctask.exception.BadRequestException;
import com.wanderlei.moviecatalog.model.api.resources.ImageResource;
import com.wanderlei.moviecatalog.model.entity.Image;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Wanderlei Santos on 03/08/2016.
 */
public class ImageByCastAsyncTask extends BaseAsyncTask<Long, Void, List<Image>> {

    private ImageResource resource;

    public ImageByCastAsyncTask(Context context, ImageResource resource) {
        super(context);
        this.resource = resource;
    }

    @Override
    protected AsyncTaskResult<List<Image>> doInBackground(Long... longs) {
        try{
            Response<List<Image>> response = resource.getListByPerson(longs[0], getAPiKey()).execute();
            switch (response.code()){
                case HTTP_OK:
                    return new AsyncTaskResult<>(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }

        } catch (IOException e){
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
