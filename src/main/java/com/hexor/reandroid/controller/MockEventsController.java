package com.hexor.reandroid.controller;

import com.hexor.reandroid.persistence.service.JmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karan on 13/3/17.
 */
@RestController
public class MockEventsController {

    @Autowired JmsClient jsmClient;

    @RequestMapping(value="/produce")
    public String produce(@RequestParam("msg")String msg){
        jsmClient.send(msg);
        return "Done";
    }

    @RequestMapping(value="/receive")
    public void receive(@RequestParam(required = false, value = "requestId") String requestId){

        jsmClient.pythonResponseMock(requestId);

        //return jsmClient.receive();
    }
}