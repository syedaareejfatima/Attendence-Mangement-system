package com.example.attendancemanager;

import java.io.Serializable;

public class Student implements Serializable  {
    
    private String RollNumber;
    private String Name;
    private String SectionId;
    private String CIDS;

    public Student(String RollNumber, String Name, String SectionId, String CIDS) {
        this.RollNumber = RollNumber;
        this.Name = Name;
        this.SectionId = SectionId;
        this.CIDS = CIDS;
    }
    
    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSectionId() {
        return SectionId;
    }

    public void setSectionId(String sectionId) {
        SectionId = sectionId;
    }

    public String getCIDS() {
        return CIDS;
    }

    public void setCIDS(String CIDS) {
        this.CIDS = CIDS;
    }
}
