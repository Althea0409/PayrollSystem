package com.lsu.dao;

import com.lsu.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayDao {

    private final DbUtil dbUtil;

    public PayDao() {
        dbUtil = new DbUtil(); // 实例化 DbUtil 对象
    }

    // 保存员工工资信息到数据库
    public boolean savePayInfo(int staffId, double basicPay, double positionPay, double houseAllowance, double allowance, double unionFee, double weFee, double houseFund, double pension, double bonusPenalty) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection(); // 使用 DbUtil 获取连接
            String sql = "INSERT INTO pay (StaffID, BasicPay, PositionPay, HouseAllowance, Allowance, UnionFee, WEFee, HouseFund, Pension, BonusPenalty) " +
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
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) dbUtil.closeCon(con); // 使用 DbUtil 关闭连接
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
