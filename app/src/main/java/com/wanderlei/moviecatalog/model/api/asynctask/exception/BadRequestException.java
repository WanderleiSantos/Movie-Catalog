package com.wanderlei.moviecatalog.model.api.asynctask.exception;

/**
 * Created by wanderlei on 05/03/16.
 */
public class BadRequestException extends Exception {

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
