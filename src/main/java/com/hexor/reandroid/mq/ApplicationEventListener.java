package com.hexor.reandroid.mq;

import com.hexor.reandroid.controller.HomeController;
import org.apache.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by karan on 2/4/17.
 */
@Component
public class ApplicationEventListener {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @EventListener
    public void submissionEventHandler(SubmissionEvent event) throws IOException {

        logger.info("inside submissionEventHandler...");

        long submissionId = event.getSubmissionId();
        String message = event.getMessage();
        SseEmitter sseEmitter = sseEmitters.get(submissionId);

        if ( sseEmitter == null ) {
            logger.warn(String.format("CANNOT get the SseEmitter for submission #%d.", submissionId));
            return;
        }
        sseEmitter.send(message);
    }

    public void addSseEmitters(long submissionId, SseEmitter sseEmitter) {
        sseEmitters.put(submissionId, sseEmitter);
    }

    /**
     * The list of the objects of SseEmitter.
     * The key of the map stands for submissionId.
     * The value of the map is the corresponding SseEmitter object.
     */
    private static Map<Long, SseEmitter> sseEmitters = new Hashtable<>();
}
