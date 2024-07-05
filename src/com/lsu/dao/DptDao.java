package com.lsu.dao;

import com.lsu.model.Dpt;
import com.lsu.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DptDao {
    private final DbUtil dbUtil;

    public DptDao() {
        dbUtil = new DbUtil();
    }

    // 保存部门信息到数据库
    public int saveDptInfo(int dptId, String dptName, String dptHead, int dptMembers) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        // 通过预编译的SQL语句将信息插入到数据库中。
        try {
            con = dbUtil.getConnection();
            String sql = "INSERT INTO department (DptId, DptName, DptHead, DptMembers) " +
                    "VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dptId);
            pstmt.setString(2, dptName);
            pstmt.setString(3, dptHead);
            pstmt.setInt(4, dptMembers);
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

    // 获取所有部门信息
    public List<Dpt> getAllDpt() throws Exception {
        List<Dpt> dptList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 通过预编译的SQL语句从数据库中获取所有部门信息，并将其封装成Dpt对象放入List集合中返回。
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM department";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Dpt dpt = new Dpt(
                        rs.getInt("DptId"),
                        rs.getString("DptName"),
                        rs.getString("DptHead"),
                        rs.getInt("DptMembers")
                );
                dptList.add(dpt);
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
        return dptList;
    }

    // 更新部门信息
    public int updateDpt(Dpt dpt) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        // 通过预编译的SQL语句更新数据库中的部门信息
        try {
            con = dbUtil.getConnection();
            String sql = "UPDATE department SET DptName=?, DptHead=?, DptMembers=? WHERE DptId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dpt.getDptName());
            pstmt.setString(2, dpt.getDptHead());
            pstmt.setInt(3, dpt.getDptMembers());
            pstmt.setInt(4, dpt.getDptId());
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

    // 删除部门信息
    public int deleteDpt(int dptId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        // 通过预编译的SQL语句删除数据库中的部门信息
        try {
            con = dbUtil.getConnection();
            String sql = "DELETE FROM department WHERE DptId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dptId);
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


}

