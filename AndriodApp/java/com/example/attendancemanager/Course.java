package com.example.attendancemanager;

import java.io.Serializable;

public class Course implements Serializable  {

    private String id;
    private String CourseCode;
    private String Description;

    public Course(String id, String CourseCode, String Description) {
        this.id = id;
        this.CourseCode = CourseCode;
        this.Description = Description;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String name) {
        CourseCode = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}