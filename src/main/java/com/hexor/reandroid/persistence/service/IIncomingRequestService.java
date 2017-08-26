package com.hexor.reandroid.persistence.service;

import com.hexor.reandroid.persistence.entity.IncomingRequest;

/**
 * Created by karan on 11/3/17.
 */
public interface IIncomingRequestService {

    public IncomingRequest persistRequest(IncomingRequest incomingRequest);

    public IncomingRequest getIncomingRequestById(Integer requestId);

    void updateIncomingRequest(IncomingRequest incomingRequest);
}
