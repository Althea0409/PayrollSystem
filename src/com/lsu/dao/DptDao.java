package com.lsu.dao;

import com.lsu.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DptDao {

    private final DbUtil dbUtil;

    public DptDao() {
        dbUtil = new DbUtil(); // 实例化 DbUtil 对象
    }

    // 保存部门信息到数据库
    public boolean saveDepartmentInfo(int dptId, String dptName, String dptHead, int dptNum) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection(); // 使用 DbUtil 获取连接
            String sql = "INSERT INTO department (DptId, DptName, DptHead, DptNum) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dptId);
            pstmt.setString(2, dptName);
            pstmt.setString(3, dptHead);
            pstmt.setInt(4, dptNum);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
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
