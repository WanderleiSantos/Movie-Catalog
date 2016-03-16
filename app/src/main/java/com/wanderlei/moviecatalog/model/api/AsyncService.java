package com.wanderlei.moviecatalog.model.api;

import com.wanderlei.moviecatalog.model.api.asynctask.ApiResultListner;

/**
 * Created by wanderlei on 05/03/16.
 */
public interface AsyncService {

    ApiResultListner getServiceApiResultListner();

    void setServiceApiResultListner(ApiResultListner apiResultListner);

    void cancelAllService();
}
