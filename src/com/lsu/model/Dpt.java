package com.lsu.model;

public class Dpt {
    private Integer dptId;
    private String dptName;
    private String dptHead;
    private Integer dptMembers;

    public Dpt(Integer dptId, String dptName, String dptHead, Integer dptMembers) {
        this.dptId = dptId;
        this.dptName = dptName;
        this.dptHead = dptHead;
        this.dptMembers = dptMembers;
    }

    // Getter and Setter
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

    public String getDptHead() {
        return dptHead;
    }
    public void setDptHead(String dptHead) {
        this.dptHead = dptHead;
    }

    public Integer getDptMembers() {
        return dptMembers;
    }
    public void setDptMembers(Integer dptMembers) {
        this.dptMembers = dptMembers;
    }

    // 将部门对象转换为对象数组
    public Object[] toObjectArray() {
        return new Object[]{
                dptId, dptName, dptHead, dptMembers
        };
    }
}
