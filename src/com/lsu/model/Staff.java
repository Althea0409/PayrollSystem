package com.lsu.model;

public class Staff {
    private Integer StaffId;
    private String Name;
    private String Gender;
    private String BirthDate;
    private String JoinDate;
    private Integer DptId;
    private String DptName;
    private String Position;
    private String Title;
    private String PolStatus;
    private String MarStatus;

    // getter and setter
    public int getStaffId() {
        return StaffId;
    }
    public void setStaffId(int StaffId) {
        this.StaffId=StaffId;
    }

    public String getName() {
        return Name;
    }
    public void set(String Name) {
        this.Name=Name;
    }

    public String getGender() {
        return Gender;
    }
    public void setGender(String Gender) {
        this.Gender=Gender;
    }

    public String getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(String BirthDate) {
        this.BirthDate=BirthDate;
    }

    public String getJoinDate() {
        return JoinDate;
    }
    public void setJoinDate(String JoinDate) {
        this.JoinDate=JoinDate;
    }

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

    public String getPosition() {
        return Position;
    }
    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPolStatus() {
        return PolStatus;
    }
    public void setPolStatus(String PolStatus) {
        this.PolStatus = PolStatus;
    }

    public String getMarStatus() {
        return MarStatus;
    }
    public void setMarStatus(String MarStatus) {
        this.MarStatus = MarStatus;
    }

}
