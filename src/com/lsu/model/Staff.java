package com.lsu.model;

public class Staff {
    private Integer staffId;
    private String name;
    private String gender;
    private String birthDate;
    private String joinDate;
    private Integer dptId;
    private String dptName;
    private String position;
    private String title;
    private String polStatus;
    private String marStatus;

    public Staff() {

    }

    public Staff(Integer staffId, String name, String gender, String birthDate, String joinDate, Integer dptId, String dptName, String position, String title, String polStatus, String marStatus) {
        this.staffId = staffId;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.dptId = dptId;
        this.dptName = dptName;
        this.position = position;
        this.title = title;
        this.polStatus = polStatus;
        this.marStatus = marStatus;
    }

    // Getter and Setter
    public Integer getStaffId() {
        return staffId;
    }
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getDptId() {
        return dptId;
    }
    public void setDptId(Integer dptId) {
        this.dptId = dptId;
    }

    public String getDptName() {
        return dptName;
    }
    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPolStatus() {
        return polStatus;
    }
    public void setPolStatus(String polStatus) {
        this.polStatus = polStatus;
    }

    public String getMarStatus() {
        return marStatus;
    }
    public void setMarStatus(String marStatus) {
        this.marStatus = marStatus;
    }

    // 将员工对象转换为对象数组
    public Object[] toObjectArray() {
        return new Object[]{staffId, name, gender, birthDate, joinDate, dptId, dptName, position, title, polStatus, marStatus};
    }
}
