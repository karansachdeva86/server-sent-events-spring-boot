package com.hexor.reandroid.controller;

import com.hexor.reandroid.persistence.entity.IncomingRequest;
import com.hexor.reandroid.persistence.service.IIncomingRequestService;
import com.hexor.reandroid.persistence.service.JmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by karan on 13/3/17.
 */
@RestController
public class MockEventsController {

    @Autowired JmsClient jsmClient;

    @Autowired private IIncomingRequestService incomingRequestService;

    @RequestMapping(value="/produce")
    public String produce(@RequestParam("msg")String msg){
        jsmClient.send(msg);
        return "Done";
    }

    @RequestMapping(value="/receive")
    @ResponseBody
    public String receive(@RequestParam(required = false, value = "requestId") String requestId){


        IncomingRequest incomingRequest = incomingRequestService.getIncomingRequestById(Integer.parseInt(requestId));

        Path file1 = Paths.get(incomingRequest.getFileName());

        BasicFileAttributes attr = null;//Reading basic attribute/metadata of the file
        try {
            attr = Files.readAttributes(file1, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // extracting the attribute/metadata of the file -- In real life this could be a long process

        StringBuffer buffer = new StringBuffer();

        buffer.append("<br/>" +"<b>Creation Time : " + attr.creationTime() + "</b><br/>");
        buffer.append("<br/>" + "<b>Last Access Time : " + attr.lastAccessTime()  + "</b><br/>");
        buffer.append("<br/>" + "<b>Last Modified Time :" + attr.lastModifiedTime()  + "</b><br/>");
        buffer.append("<br/>" + "<b>Is Directory? : " + attr.isDirectory()  + "</b><br/>");
        buffer.append("<br/>" + "<b>Is Other? : " + attr.isOther()  + "</b><br/>");
        buffer.append("<br/>" + "<b>is Regular File? : " + attr.isRegularFile()  + "</b><br/>");
        buffer.append("<br/>" + "<b>iSize of File : " + attr.size()  + "</b><br/>");


        incomingRequest.setFileDetail(buffer.toString());

        incomingRequestService.updateIncomingRequest(incomingRequest);


        // Marking the processing as complete
        jsmClient.pythonResponseMock(requestId);

        return "File Processed Successfully. File data has been stored in the database and processing information has been posted on PROCESSED queue successfully";
        //return jsmClient.receive();
    }
}