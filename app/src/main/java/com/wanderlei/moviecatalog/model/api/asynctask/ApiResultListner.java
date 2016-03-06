package com.wanderlei.moviecatalog.model.api.asynctask;

/**
 * Created by wanderlei on 05/03/16.
 */
public interface ApiResultListner {

    public void onResult(Object object);

    public void onExecption(Exception exception);
}
