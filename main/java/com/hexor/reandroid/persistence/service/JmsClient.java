package com.hexor.reandroid.persistence.service;

/**
 * Created by karan on 13/3/17.
 */
public interface JmsClient {
    public void send(String msg);
    public String receive();
    public void pythonResponseMock(String msg);
}