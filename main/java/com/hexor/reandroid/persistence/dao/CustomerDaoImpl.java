package com.hexor.reandroid.persistence.dao;

import com.hexor.reandroid.persistence.entity.CustomerEntity;
import com.hexor.reandroid.persistence.facade.AbstractFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.sql.SQLException;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractFacade<CustomerEntity> implements CustomerDao {

    private static final Logger LOG               = Logger.getLogger(CustomerDaoImpl.class);
    private static final String keyStoreType      = "JCEKS";
    private static       String keyStoreAlgorithm = "DES";

    public CustomerDaoImpl() {
        super(CustomerEntity.class);
    }


    @Override
    public void addCustomer(CustomerEntity customerEntity) {
        this.create(customerEntity);
    }

    @Override
    public void updateCustomer(CustomerEntity customerEntity) {
        this.edit(customerEntity);

    }

    @Override
    public void removeCustomer(CustomerEntity customerEntity) {
        this.remove(customerEntity);

    }




    @Override
    public CustomerEntity findCustomerByName(String name) throws DaoException{
        Session session = this.getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(CustomerEntity.class);
        CustomerEntity customerEntity = (CustomerEntity) criteria.add(Restrictions.eq("name", name))
                .uniqueResult();
        if(customerEntity == null)
            throw new DaoException("Unknown Customer Identifier, "+name);
        return customerEntity;
    }

    @Override
    public CustomerEntity findCustomerById(String id) throws DaoException {
        CustomerEntity customerEntity = null;
        try {
            customerEntity = this.find(id);
        }catch (Exception e){
            throw new DaoException("Unknown Customer Identifier, "+id);
        }
        return customerEntity;
    }

}
