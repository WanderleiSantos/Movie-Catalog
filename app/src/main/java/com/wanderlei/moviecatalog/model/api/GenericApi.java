package com.wanderlei.moviecatalog.model.api;

import android.content.Context;

import com.wanderlei.moviecatalog.model.api.asynctask.ApiResultListner;

/**
 * Created by wanderlei on 05/03/16.
 */
public abstract class GenericApi implements AsyncService {

    private Context context;

    private ApiResultListner apiResultListner;

    public GenericApi(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ApiResultListner getServiceApiResultListner() {
        return apiResultListner;
    }

    public void setServiceApiResultListner(ApiResultListner apiResultListner) {
        this.apiResultListner = apiResultListner;
    }

    public void verifyServiceResultListner(){
        if (getServiceApiResultListner() == null){
            throw new RuntimeException("Set ApiResultListener instance");
        }
    }
}
