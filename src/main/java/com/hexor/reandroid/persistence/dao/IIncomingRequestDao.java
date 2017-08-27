package com.hexor.reandroid.persistence.dao;

import com.hexor.reandroid.persistence.entity.IncomingRequest;

/**
 * Created by karan on 13/3/17.
 */
public interface IIncomingRequestDao {
    public IncomingRequest persistAndReturnEntity(IncomingRequest incomingRequest);

    IncomingRequest getIncomingRequestById(Integer requestId);

    void updateIncomingRequest(IncomingRequest incomingRequest);
}
