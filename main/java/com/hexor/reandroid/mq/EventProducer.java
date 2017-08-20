package com.hexor.reandroid.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by karan on 12/3/17.
 */
//@Component
public class EventProducer {


//    @Autowired
//    private JmsTemplate template;
//
//
//
//    public void sendEvent(final String eventParameter) {
//        try {
//
//            final String event = eventParameter;
//            MessageCreator messageCreator = new MessageCreator() {
//                @Override public Message createMessage(Session session) throws JMSException {
//                    TextMessage message = session.createTextMessage("myEventQueue");
//                    message.setStringProperty("parameter", eventParameter);
//                    return message;
//                }
//            };
//        } catch (JmsException je) {
//            // ... handle exception
//            je.printStackTrace();
//        }
//    }

}
