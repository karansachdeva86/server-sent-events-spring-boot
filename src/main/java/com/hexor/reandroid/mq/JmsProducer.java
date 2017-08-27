package com.hexor.reandroid.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by karan on 13/3/17.
 */
@Component
public class JmsProducer {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(JmsProducer.class);


    @Autowired JmsTemplate jmsTemplate;

    @Value("${jms.queue.incoming}")
    String incomingQueue;

    public void send(String msg){

        LOG.info("uploading new APK request to the [INCOMING] queue....");

        jmsTemplate.convertAndSend(incomingQueue, msg);

        //LOG.info("Request uploaded to [INCOMING] queue....");

    }
}
