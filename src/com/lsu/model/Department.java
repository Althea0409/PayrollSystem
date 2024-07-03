package com.lsu.model;

public class Department {
    private Integer DptId;
    private String DptName;
    private String DptHead;
    private String DptNum;

    // getter and setter
    public int getDptId() {
        return DptId;
    }
    public void setDptId(int DptId) {
        this.DptId=DptId;
    }

    public String getDptName() {
        return DptName;
    }
    public void setDptName(String DptName) {
        this.DptName=DptName;
    }

    public String getDptHead() {
        return DptHead;
    }
    public void setDptHead(String DptHead) {
        this.DptHead=DptHead;
    }

    public String getDptNum() {
        return DptNum;
    }
    public void setDptNum(String DptNum) {
        this.DptNum=DptNum;
    }

}

