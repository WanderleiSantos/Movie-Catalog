package com.wanderlei.moviecatalog.model.api.asynctask.exception;

/**
 * Created by wanderlei on 05/03/16.
 */
public class HttpUnauthorizedException extends Exception {

    public HttpUnauthorizedException() {
    }

    public HttpUnauthorizedException(String message) {
        super(message);
    }
}
