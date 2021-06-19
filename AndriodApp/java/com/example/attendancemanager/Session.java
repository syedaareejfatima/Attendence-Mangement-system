package com.example.attendancemanager;

import java.io.Serializable;

public class Session implements Serializable  {

    private String Id;
    private String Time;
    private String CourseId;
    private String SIDS;
    private String Date;

    public Session(String id, String Date, String Time, String CourseId, String SIDS) {
        this.Id = id;
        this.Date = Date;
        this.Time = Time;
        this.SIDS = SIDS;
        this.CourseId = CourseId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSIDS() {
        return SIDS;
    }

    public void setSIDS(String SIDS) {
        this.SIDS = SIDS;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }
}

