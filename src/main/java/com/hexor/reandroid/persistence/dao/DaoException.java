package com.hexor.reandroid.persistence.dao;

/**
 * Created by arunnadger on 24/9/14.
 */
public class DaoException extends Exception {

    String exception;

    public DaoException(String exception) {
        this.exception = exception;
    }


    public String toString() {
        return this.getClass().getSimpleName() +": "+ exception;
    }

    public String getMessage() {
        return this.getClass().getSimpleName() +": "+ exception;
    }
}
