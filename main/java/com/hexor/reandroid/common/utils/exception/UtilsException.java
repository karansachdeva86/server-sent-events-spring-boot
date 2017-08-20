package com.hexor.reandroid.common.utils.exception;

/**
 * Created by karan on 24/9/14.
 */
public class UtilsException extends Exception {

    String exception;

    public UtilsException(String exception) {
        this.exception = exception;
    }


    public String toString() {
        return this.getClass().getSimpleName() +": "+ exception;
    }

    public String getMessage() {
        return this.getClass().getSimpleName() +": "+ exception;
    }
}
