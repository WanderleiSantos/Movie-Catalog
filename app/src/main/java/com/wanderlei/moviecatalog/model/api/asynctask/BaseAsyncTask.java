package com.wanderlei.moviecatalog.model.api.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.moviecatalog.R;

/**
 * Created by wanderlei on 05/03/16.
 */
public abstract class BaseAsyncTask<PARAM, PROGRESS, RETURN> extends AsyncTask<PARAM, PROGRESS, AsyncTaskResult<RETURN>> {

    private Context context;
    private ApiResultListner apiResultListner;

    public BaseAsyncTask(Context context) {
        this.context = context;
    }

    public void setApiResultListner(ApiResultListner apiResultListner) {
        this.apiResultListner = apiResultListner;
    }

    public Context getContext() {
        return context;
    }

    public String getBaseUrl(){
        return context.getString(R.string.base_url);
    }

    public String getAPiKey(){
        return context.getString(R.string.api_key);
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<RETURN> returnAsyncTaskResult) {
        if(returnAsyncTaskResult.getResult() != null){
            apiResultListner.onResult(returnAsyncTaskResult.getResult());
        } else if (returnAsyncTaskResult.getException() != null){
            apiResultListner.onExecption(returnAsyncTaskResult.getException());
        } else {
            apiResultListner.onResult(returnAsyncTaskResult.getResult());
        }
    }
}
