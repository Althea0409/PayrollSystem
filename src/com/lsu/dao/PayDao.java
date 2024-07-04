package com.lsu.dao;

import com.lsu.model.Pay;
import com.lsu.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayDao {
    private DbUtil dbUtil;

    public PayDao() {
        dbUtil = new DbUtil();
    }

    // 保存工资信息到数据库
    public int savePay(int staffId, double basicPay, double positionPay, double houseAllowance, double allowance, double unionFee, double weFee, double houseFund, double pension, double bonusPenalty) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection(); // 使用 DbUtil 获取连接
            String sql = "INSERT INTO pay (StaffId, BasicPay, PositionPay, HouseAllowance, Allowance, UnionFee, WEFee, HouseFund, Pension, BonusPenalty) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, staffId);
            pstmt.setDouble(2, basicPay);
            pstmt.setDouble(3, positionPay);
            pstmt.setDouble(4, houseAllowance);
            pstmt.setDouble(5, allowance);
            pstmt.setDouble(6, unionFee);
            pstmt.setDouble(7, weFee);
            pstmt.setDouble(8, houseFund);
            pstmt.setDouble(9, pension);
            pstmt.setDouble(10, bonusPenalty);
            return pstmt.executeUpdate();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbUtil.closeCon(con);
        }
    }

    // 获取所有员工的工资信息
    public List<Pay> getAllPay() throws Exception{
        List<Pay> payList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM pay";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Pay pay = new Pay();
                pay.setStaffId(rs.getInt("StaffId"));
                pay.setBasicPay(rs.getDouble("BasicPay"));
                pay.setPositionPay(rs.getDouble("PositionPay"));
                pay.setHouseAllowance(rs.getDouble("HouseAllowance"));
                pay.setAllowance(rs.getDouble("Allowance"));
                pay.setUnionFee(rs.getDouble("UnionFee"));
                pay.setWeFee(rs.getDouble("WEFee"));
                pay.setHouseFund(rs.getDouble("HouseFund"));
                pay.setPension(rs.getDouble("Pension"));
                pay.setBonusPenalty(rs.getDouble("BonusPenalty"));
                payList.add(pay);
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbUtil.closeCon(con);
        }
        return payList;
    }

    // 更新员工的工资信息
    public int updatePay(Pay pay) throws Exception{
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection();
            String sql = "UPDATE pay SET BasicPay=?, PositionPay=?, HouseAllowance=?, Allowance=?, UnionFee=?, WEFee=?, HouseFund=?, Pension=?, BonusPenalty=? WHERE StaffID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, pay.getBasicPay());
            pstmt.setDouble(2, pay.getPositionPay());
            pstmt.setDouble(3, pay.getHouseAllowance());
            pstmt.setDouble(4, pay.getAllowance());
            pstmt.setDouble(5, pay.getUnionFee());
            pstmt.setDouble(6, pay.getWeFee());
            pstmt.setDouble(7, pay.getHouseFund());
            pstmt.setDouble(8, pay.getPension());
            pstmt.setDouble(9, pay.getBonusPenalty());
            pstmt.setInt(10, pay.getStaffId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbUtil.closeCon(con);
        }
    }

    // 删除员工的工资信息
    public int deletePay(int staffId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection();
            String sql = "DELETE FROM pay WHERE StaffId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, staffId);
            return pstmt.executeUpdate();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbUtil.closeCon(con);
        }
    }

    // 根据员工编号获取工资信息
    public Pay getPayByStaffID(int staffID) throws Exception{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pay pay = null;
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM pay WHERE StaffID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, staffID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pay = new Pay();
                pay.setStaffId(rs.getInt("StaffId"));
                pay.setBasicPay(rs.getDouble("BasicPay"));
                pay.setPositionPay(rs.getDouble("PositionPay"));
                pay.setHouseAllowance(rs.getDouble("HouseAllowance"));
                pay.setAllowance(rs.getDouble("Allowance"));
                pay.setUnionFee(rs.getDouble("UnionFee"));
                pay.setWeFee(rs.getDouble("WEFee"));
                pay.setHouseFund(rs.getDouble("HouseFund"));
                pay.setPension(rs.getDouble("Pension"));
                pay.setBonusPenalty(rs.getDouble("BonusPenalty"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbUtil.closeCon(con);
        }
        return pay;
    }
}




