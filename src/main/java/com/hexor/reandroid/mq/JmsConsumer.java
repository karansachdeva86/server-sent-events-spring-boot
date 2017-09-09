package com.hexor.reandroid.mq;

import com.hexor.reandroid.common.utils.StringUtils;
import com.hexor.reandroid.controller.HomeController;
import com.hexor.reandroid.persistence.entity.IncomingRequest;
import com.hexor.reandroid.persistence.service.IIncomingRequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;

/**
 * Created by karan on 13/3/17.
 */
@Component
public class JmsConsumer implements MessageListener {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JmsConsumer.class);


    @Autowired JmsTemplate jmsTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IIncomingRequestService iIncomingRequestService;

    @Value("${jms.queue.processed}")
    String processedQueue;


    public String receive(){
        logger.info("inside receive");
        return (String)jmsTemplate.receiveAndConvert(processedQueue);
    }

    @Override public void onMessage(Message message) {

        logger.info("APK Processing completion request received....");
        //if (message instanceof MapMessage) {
        try {
           // final MapMessage mapMessage = (MapMessage) message;
            logger.info("---------------------START--------------------------------");

                TextMessage msg = (TextMessage) message;
            logger.info("Received Response for Request ID_# " + msg.getText());
                //String event = mapMessage.getString("event");
            long i =0L;
            long submissionId = i++;


            Integer requestId = Integer.parseInt(msg.getText());

            if(requestId != null && !StringUtils.isBlank(msg.getText())) {
                logger.info("Fetching details of the request from database. Request Id#" + requestId);
                IncomingRequest incomingRequest = iIncomingRequestService.getIncomingRequestById(requestId);
                if(incomingRequest !=null) {
                    incomingRequest.setStatus("PROCESSED");
                    iIncomingRequestService.updateIncomingRequest(incomingRequest);
                    logger.info("Updating Request Status to PROCESSED. Request Id#" + requestId);
                }else {
                    logger.info("Invalid requestId received on PROCESSED queue. ID#" + requestId);
                }
            }else{
                logger.info("requestId received was null");
            }


            eventPublisher.publishEvent(new SubmissionEvent(this, submissionId, "Message"));


            } catch (JMSException e) {
                e.printStackTrace();
            logger.error("Error Occured");
            }
       // }
        logger.info("---------------------END--------------------------------");
    }
}
