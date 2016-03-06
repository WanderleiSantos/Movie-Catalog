package com.wanderlei.moviecatalog.model.api.asynctask;

/**
 * Created by wanderlei on 05/03/16.
 */
public class AsyncTaskResult<T> {

    private T result;
    private Exception exception;

    public AsyncTaskResult() {
    }

    public AsyncTaskResult(T result) {
        this.result = result;
    }

    public AsyncTaskResult(Exception exception) {
        this.exception = exception;
    }

    public T getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }
}
