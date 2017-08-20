package com.hexor.reandroid.persistence.dao;

import com.hexor.reandroid.persistence.entity.CustomerEntity;
import com.hexor.reandroid.persistence.entity.IncomingRequest;
import com.hexor.reandroid.persistence.facade.AbstractFacade;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by karan on 13/3/17.
 */

@Repository("incomingRequestDao")
@Transactional
public class IncomingRequestDaoImpl extends AbstractFacade<IncomingRequest> implements  IIncomingRequestDao {

    public IncomingRequestDaoImpl() {
        super(IncomingRequest.class);
    }


    @Override
    public IncomingRequest persistAndReturnEntity(IncomingRequest incomingRequest) {
        return this.createAndReturn(incomingRequest);
    }

    @Override
    public IncomingRequest getIncomingRequestById(Integer requestId) {
        return this.find(requestId);
    }

    @Override
    public void updateIncomingRequest(IncomingRequest incomingRequest) {
        this.edit(incomingRequest);
    }
}
