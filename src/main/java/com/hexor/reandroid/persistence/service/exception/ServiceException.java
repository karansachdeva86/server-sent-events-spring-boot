package com.hexor.reandroid.persistence.service.exception;

/**
 * Created by karan on 24/9/14.
 */
public class ServiceException extends Exception {

    String exception;

    public ServiceException(String exception) {
        this.exception = exception;
    }


    public String toString() {
        return this.getClass().getSimpleName() +": "+ exception;
    }

    public String getMessage() {
        return this.getClass().getSimpleName() +": "+ exception;
    }
}
