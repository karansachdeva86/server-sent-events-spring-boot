package com.hexor.reandroid.persistence.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by karan on 11/3/17.
 */

@Entity
@Table(name="incoming_request")
public class IncomingRequest implements  java.io.Serializable{

    private int id;
    private String emailId;
    private Integer userId;
    private boolean validFile;
    private Date uploadDate;
    private String fileName;
    private String status;


    @Id
    @GeneratedValue
    @Column(name="id",unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="email_id",unique = false)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name="user_id",unique = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name="valid_file",unique = false)
    public boolean isValidFile() {
        return validFile;
    }

    public void setValidFile(boolean validFile) {
        this.validFile = validFile;
    }

    @Column(name="upload_date",unique = false)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Column(name="file_name",unique = false)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name="status",unique = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
