package com.lsu.dao;

import com.lsu.model.Staff;
import com.lsu.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private DbUtil dbUtil;

    public StaffDao() {
        dbUtil = new DbUtil();
    }

    // 保存员工信息
    public int saveStaffInfo(int staffId, String name, String gender, String birthDate, String joinDate, int dptId,
                             String dptName, String position, String title, String polStatus, String marStatus) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection();
            String sql = "INSERT INTO staff (StaffID, Name, Gender, BirthDate, JoinDate, DptID, DptName, Position, Title, PolStatus, MarStatus) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, staffId);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setString(4, birthDate);
            pstmt.setString(5, joinDate);
            pstmt.setInt(6, dptId);
            pstmt.setString(7, dptName);
            pstmt.setString(8, position);
            pstmt.setString(9, title);
            pstmt.setString(10, polStatus);
            pstmt.setString(11, marStatus);
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

    // 获取所有员工信息
    public List<Staff> getAllStaff() throws Exception {
        List<Staff> staffList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM staff";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        rs.getString("BirthDate"),
                        rs.getString("JoinDate"),
                        rs.getInt("DptID"),
                        rs.getString("DptName"),
                        rs.getString("Position"),
                        rs.getString("Title"),
                        rs.getString("PolStatus"),
                        rs.getString("MarStatus")
                );
                staffList.add(staff);
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
        return staffList;
    }

    // 更新员工信息
    public int updateStaffInfo(Staff staff) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection();
            String sql = "UPDATE staff SET Name=?, Gender=?, BirthDate=?, JoinDate=?, DptID=?, DptName=?, Position=?, Title=?, PolStatus=?, MarStatus=? WHERE StaffID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, staff.getName());
            pstmt.setString(2, staff.getGender());
            pstmt.setString(3, staff.getBirthDate());
            pstmt.setString(4, staff.getJoinDate());
            pstmt.setInt(5, staff.getDptId());
            pstmt.setString(6, staff.getDptName());
            pstmt.setString(7, staff.getPosition());
            pstmt.setString(8, staff.getTitle());
            pstmt.setString(9, staff.getPolStatus());
            pstmt.setString(10, staff.getMarStatus());
            pstmt.setInt(11, staff.getStaffId());
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

    // 删除员工信息
    public int deleteStaffInfo(int staffId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection();
            String sql = "DELETE FROM staff WHERE StaffID=?";
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
}
