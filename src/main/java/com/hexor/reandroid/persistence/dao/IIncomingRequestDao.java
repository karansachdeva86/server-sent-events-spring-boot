package com.hexor.reandroid.persistence.dao;

import com.hexor.reandroid.persistence.entity.IncomingRequest;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by karan on 13/3/17.
 */
@Transactional
public interface IIncomingRequestDao extends CrudRepository<IncomingRequest, Integer> {
//
//    IncomingRequest persistAndReturnEntity(IncomingRequest incomingRequest);
//
//    IncomingRequest getIncomingRequestById(Integer requestId);
//
//    void updateIncomingRequest(IncomingRequest incomingRequest);
}
