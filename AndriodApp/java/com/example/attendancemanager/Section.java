package com.example.attendancemanager;

import java.io.Serializable;


public class Section implements Serializable  {

    private String Id;
    private String Batch;
    private String Code;

    public Section(String id, String Batch, String Code) {
        this.Id = id;
        this.Batch = Batch;
        this.Code = Code;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

