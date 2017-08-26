package com.hexor.reandroid.persistence.service.exception;

/**
 * Created by milanashara on 1/3/16.
 */
public class CustomerSetupException extends Exception {

    private static final long serialVersionUID = 1L;
    String exception;

    public CustomerSetupException(String exception){
        this.exception=exception;
    }

    public String toString() {
        return this.getClass().getSimpleName()+": "+exception;
    }

    public String getMessage(){
        return exception;
    }
}
