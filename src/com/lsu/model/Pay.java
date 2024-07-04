package com.lsu.model;

public class Pay {
    private Integer staffId;
    private String name;
    private double basicPay;
    private double positionPay;
    private double houseAllowance;
    private double allowance;
    private double unionFee;
    private double weFee;
    private double houseFund;
    private double pension;
    private double bonusPenalty;

    public Pay() {
    }

    public Pay(int staffId, double basicPay, double positionPay, double houseAllowance, double allowance, double unionFee, double weFee, double houseFund, double pension, double bonusPenalty) {
        this.staffId = staffId;
        this.basicPay = basicPay;
        this.positionPay = positionPay;
        this.houseAllowance = houseAllowance;
        this.allowance = allowance;
        this.unionFee = unionFee;
        this.weFee = weFee;
        this.houseFund = houseFund;
        this.pension = pension;
        this.bonusPenalty = bonusPenalty;
    }

    // Getters and Setters
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

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public double getPositionPay() {
        return positionPay;
    }

    public void setPositionPay(double positionPay) {
        this.positionPay = positionPay;
    }

    public double getHouseAllowance() {
        return houseAllowance;
    }

    public void setHouseAllowance(double houseAllowance) {
        this.houseAllowance = houseAllowance;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getUnionFee() {
        return unionFee;
    }

    public void setUnionFee(double unionFee) {
        this.unionFee = unionFee;
    }

    public double getWeFee() {
        return weFee;
    }

    public void setWeFee(double weFee) {
        this.weFee = weFee;
    }

    public double getHouseFund() {
        return houseFund;
    }

    public void setHouseFund(double houseFund) {
        this.houseFund = houseFund;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public double getBonusPenalty() {
        return bonusPenalty;
    }

    public void setBonusPenalty(double bonusPenalty) {
        this.bonusPenalty = bonusPenalty;
    }

    // 将工资对象转换为对象数组
    public Object[] toObjectArray() {
        return new Object[] {
                staffId, basicPay, positionPay, houseAllowance, allowance, unionFee, weFee, houseFund, pension, bonusPenalty
        };
    }
}
