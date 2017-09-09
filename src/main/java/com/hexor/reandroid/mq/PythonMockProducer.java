package com.hexor.reandroid.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by karan on 13/8/17.
 */
@Component
public class PythonMockProducer {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PythonMockProducer.class);


    @Autowired JmsTemplate jmsTemplate;

    @Value("${jms.queue.processed}")
    String processedQueue;

    public void send(String msg){

        LOG.info("uploading new APK request to the ["+processedQueue+"] queue");

        jmsTemplate.convertAndSend(processedQueue, msg);

        //LOG.info("Request uploaded to [INCOMING] queue....");

    }
}
