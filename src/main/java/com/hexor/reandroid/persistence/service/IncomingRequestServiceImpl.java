package com.hexor.reandroid.persistence.service;

import com.hexor.reandroid.persistence.dao.IIncomingRequestDao;
import com.hexor.reandroid.persistence.entity.IncomingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karan on 11/3/17.
 */

@Service("incomingRequestService")
public class IncomingRequestServiceImpl  implements  IIncomingRequestService{

    @Autowired
    private IIncomingRequestDao incomingRequestDao;

    @Override
    public IncomingRequest persistRequest(IncomingRequest incomingRequest) {
        return incomingRequestDao.save(incomingRequest);
    }

    @Override
    public IncomingRequest getIncomingRequestById(Integer requestId) {
        return incomingRequestDao.findOne(requestId);
    }

    @Override
    public void updateIncomingRequest(IncomingRequest incomingRequest) {
        incomingRequestDao.save(incomingRequest);

    }

//
//    @Override public IncomingRequest persistRequest(IncomingRequest incomingRequest) {
//        return incomingRequestDao.persistAndReturnEntity(incomingRequest);
//    }
//
//    @Override
//    public IncomingRequest getIncomingRequestById(Integer requestId) {
//        return  incomingRequestDao.getIncomingRequestById(requestId);
//    }
//
//    @Override
//    public void updateIncomingRequest(IncomingRequest incomingRequest) {
//         incomingRequestDao.updateIncomingRequest(incomingRequest);
//    }

}
