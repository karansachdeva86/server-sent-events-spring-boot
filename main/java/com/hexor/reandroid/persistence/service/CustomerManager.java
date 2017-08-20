package com.hexor.reandroid.persistence.service;

import com.hexor.reandroid.persistence.entity.CustomerEntity;
import com.hexor.reandroid.persistence.service.exception.ServiceException;

public interface CustomerManager {
    public CustomerEntity findCustomerByName(String name) throws ServiceException;
    public CustomerEntity findCustomerById(String id) throws ServiceException;

}
