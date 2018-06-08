package com.hexor.reandroid.controller;

import com.google.gson.Gson;
import com.hexor.reandroid.mq.ApplicationEventListener;
import com.hexor.reandroid.persistence.dao.IIncomingRequestDao;
import com.hexor.reandroid.persistence.entity.IncomingRequest;
import com.hexor.reandroid.persistence.service.IIncomingRequestService;
import com.hexor.reandroid.persistence.service.JmsClient;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import  javax.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by karan on 23/7/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final List<SseEmitter> emitters = new ArrayList<>();


    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private IIncomingRequestService incomingRequestService;

    @Autowired
    private IIncomingRequestDao incomingRequestDao;


    @Autowired JmsClient jmsClient;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${application.message:test}")
    private String message = "Hello World";

    @RequestMapping("")
    public String home(ModelMap model){

        logger.info("Homepage requested...");

        model.addAttribute("date", (new Date()).toString());

        logger.info("Homepage rendered successfully");

        return "home";
    }

    @RequestMapping(value="upload", method=RequestMethod.POST)
    @ResponseBody
    public void addFile(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {

        logger.info("File upload request received..");

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");

        File temp_file = new File(uploadPath + multipartFile.getOriginalFilename());

        multipartFile.transferTo(temp_file);


        //Persist The Request

        IncomingRequest incomingRequest = new IncomingRequest();


        //Validate the request

        incomingRequest.setEmailId(dmhsRequest.getRequest().getParameter("emailId"));
        incomingRequest.setFileName(uploadPath + multipartFile.getOriginalFilename());
        incomingRequest.setUploadDate(new Date());
        incomingRequest.setStatus("ACCEPTED");
        incomingRequest.setValidFile(true);


        IncomingRequest incomingRequestWithId =  incomingRequestService.persistRequest(incomingRequest);


        if(incomingRequestWithId.isValidFile()) {
            jmsClient.send(Integer.toString(incomingRequestWithId.getId()));
            //modelMap.addAttribute("msg","Please wait...");

        }else{
            //delete the file from disc
            //throw error on screen
           // modelMap.addAttribute("msg","There was a problem");

        }

        response.setContentType( "application/json" );
        response.setCharacterEncoding( "UTF-8" );

        Map<String, Object> result = new HashMap<>();

        result.put("success", true);
        result.put("msg", "Upload successful. Please wait for the processing to complete..");
        result.put("id", incomingRequestWithId.getId());

        try
        {
            response.getWriter().write( new Gson().toJson( result ) );
            System.out.println( result );
        }
        catch( IOException e )
        {
            System.out.println( "failed" );
            logger.debug( "Unable to get parse request", e );
        }
        return;


    }


    @RequestMapping("listen-for-response")
    @ResponseBody
    public SseEmitter handleRequest (HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
                                     @RequestParam(required = false, value = "id") Integer requestId) {

        logger.info("Server is attempting to check whether response is ready for request...." + requestId);
        final SseEmitter emitter = new SseEmitter(180_000L);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {

            for (int i = 0; i < 100; i++) {
                try {

                    Thread.sleep(2000);
                    IncomingRequest incomingRequest = incomingRequestService.getIncomingRequestById(requestId);


                    String status = incomingRequest.getStatus();
                        logger.info("status:" + status);

                        if ("PROCESSED".equals(status)) {
                            logger.info("sending message to the browser11..");
                            emitter.send("Backend processing is complete", MediaType.APPLICATION_JSON);
                            emitter.complete();
                        }else{
                        }


                }  catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }

        });



        return emitter;
    }


    @RequestMapping("process-file")
    public String processFile(ModelMap model, @RequestParam(required = false, value = "id") int id){

        logger.info("Processing File...");

        IncomingRequest incomingRequest = incomingRequestService.getIncomingRequestById(id);

        Path file1 = Paths.get(incomingRequest.getFileName());

        BasicFileAttributes attr = null;//Reading basic attribute/metadata of the file
        try {
            attr = Files.readAttributes(file1, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // extracting the attribute/metadata of the file

        StringBuffer buffer = new StringBuffer();

        buffer.append("<br/>" +"creationTime: " + attr.creationTime());
        buffer.append("<br/>" + "lastAccessTime: " + attr.lastAccessTime());
        buffer.append("<br/>" + "lastModifiedTime: " + attr.lastModifiedTime());
        buffer.append("<br/>" + "isDirectory: " + attr.isDirectory());
        buffer.append("<br/>" + "isOther: " + attr.isOther());
        buffer.append("<br/>" + "isRegularFile: " + attr.isRegularFile());


        logger.info("Processed file rendered successfully");

        model.addAttribute("fileMetaData", buffer.toString());

        return "fileDetail";
    }


}
