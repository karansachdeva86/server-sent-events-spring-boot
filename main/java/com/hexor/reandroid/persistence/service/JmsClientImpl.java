package com.hexor.reandroid.persistence.service;

import com.hexor.reandroid.mq.JmsConsumer;
import com.hexor.reandroid.mq.JmsProducer;
import com.hexor.reandroid.mq.PythonMockProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karan on 13/3/17.
 */
@Service
public class JmsClientImpl implements JmsClient{

    @Autowired JmsConsumer jmsConsumer;

    @Autowired JmsProducer jmsProducer;

    @Autowired
    PythonMockProducer pythonMockProducer;

    @Override
    public void send(String msg) {
        jmsProducer.send(msg);
    }



    @Override
    public String receive() {
        return jmsConsumer.receive();
    }

    @Override
    public void pythonResponseMock(String msg) {
        pythonMockProducer.send(msg);
    }

}