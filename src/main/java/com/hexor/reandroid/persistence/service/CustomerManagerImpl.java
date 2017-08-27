package com.hexor.reandroid.persistence.service;

import com.hexor.reandroid.persistence.dao.CustomerDao;
import com.hexor.reandroid.persistence.dao.DaoException;
import com.hexor.reandroid.persistence.entity.CustomerEntity;
import com.hexor.reandroid.persistence.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("customerManagerImpl")
public class CustomerManagerImpl implements CustomerManager {

    @Autowired
    private CustomerDao customerDao;
    
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public CustomerEntity findCustomerByName(String name) throws ServiceException{
        try {
            CustomerEntity customer =  customerDao.findCustomerByName(name);
           return customer;

        }catch(DaoException de){
            throw new ServiceException(de.getMessage());
        }
    }
    
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public CustomerEntity findCustomerById(String id) throws ServiceException{
      CustomerEntity customerEntity;
        try {
            customerEntity = customerDao.findCustomerById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unknown Customer Id, "+id);
        }
        if(null== customerEntity){
            throw new ServiceException("Unknown Customer Id, "+id);
    		}
            return customerEntity;
    }



}
