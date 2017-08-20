package com.hexor.reandroid.persistence.dao;

import com.hexor.reandroid.persistence.entity.CustomerEntity;

public interface CustomerDao {

    public void addCustomer(CustomerEntity customerEntity);
    public void updateCustomer(CustomerEntity customerEntity);
    public void removeCustomer(CustomerEntity customerEntity);
    public CustomerEntity findCustomerByName(String name) throws DaoException;
    CustomerEntity findCustomerById(String id) throws DaoException;

}
