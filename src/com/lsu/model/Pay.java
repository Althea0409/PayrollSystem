package com.lsu.model;

public class Pay {
    private Integer StaffId;
    private String Name;
    private String BasicPay;
    private String PositionPay;
    private String HouseAllowance;
    private String Allowance;
    private String UnionFee;
    private String WEFee;
    private String HouseFund;
    private String Pension;
    private String BonusPenalty;

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
    public void setName(String Name) {
        this.Name=Name;
    }

    public String BasicPay() {
        return BasicPay;
    }
    public void setBasicPay(String BasicPay) {
        this.BasicPay=BasicPay;
    }

    public String getPositionPay() {
        return PositionPay;
    }
    public void setPositionPay(String PositionPay) {
        this.PositionPay=PositionPay;
    }

    public String getHouseAllowance() {
        return HouseAllowance;
    }
    public void setHouseAllowance(String HouseAllowance) {
        this.HouseAllowance=HouseAllowance;
    }

    public String Allowance() {
        return Allowance;
    }
    public void setAllowance(String Allowance) {
        this.Allowance=Allowance;
    }

    public String getUnionFee() {
        return UnionFee;
    }
    public void setUnionFee(String UnionFee) {
        this.UnionFee=UnionFee;
    }

    public String getWEFee() {
        return WEFee;
    }
    public void setWEFee(String WEFee) {
        this.WEFee=WEFee;
    }

    public String getHouseFund() {
        return HouseFund;
    }
    public void setHouseFund(String HouseFund) {
        this.HouseFund=HouseFund;
    }

    public String getPension() {
        return Pension;
    }
    public void setPension(String Pension) {
        this.Pension=Pension;
    }

    public String getBonusPenalty() {
        return BonusPenalty;
    }
    public void setBonusPenalty(String BonusPenalty) {
        this.BonusPenalty=BonusPenalty;
    }

}
