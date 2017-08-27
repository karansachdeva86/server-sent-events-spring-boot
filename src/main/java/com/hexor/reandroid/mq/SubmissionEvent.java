package com.hexor.reandroid.mq;

import org.springframework.context.ApplicationEvent;

/**
 * Created by karan on 16/3/17.
 */
public class SubmissionEvent extends ApplicationEvent {
    public SubmissionEvent(Object source, long submissionId, String message) {
        super(source);
        this.submissionId = submissionId;
        this.message = message;
    }

    // getters and setters

    public long getSubmissionId() {
        return submissionId;
    }

    public String getMessage() {
        return message;
    }

    private final long submissionId;

    private final String message;
}
