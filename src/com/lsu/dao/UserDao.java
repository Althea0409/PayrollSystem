package com.lsu.dao;

import com.lsu.model.Users;
import com.lsu.utils.ToolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserDao {

    public Users login(Connection con, Users user)throws Exception {
        Users resultUser = null;
        String sql = "select * from user where username=? and password=? and role = ?";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getRole());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            resultUser = new Users();
            resultUser.setUserId(rs.getInt("id"));
            resultUser.setUserName(rs.getString("username"));
            resultUser.setSex(rs.getString("sex"));
            resultUser.setPhone(rs.getString("phone"));
        }
        return resultUser;
    }

    public int addUser(Connection con, Users user) throws Exception{
        //查询注册用户名是否存在
        String sql = "select * from user where userName=? ";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return 2;
        }

        sql="insert into user (username,password,role,sex,phone) values (?,?,?,?,?)";
        PreparedStatement pstmt2=(PreparedStatement) con.prepareStatement(sql);
        pstmt2.setString(1, user.getUserName());
        pstmt2.setString(2, user.getPassword());
        pstmt2.setInt(3, user.getRole());
        pstmt2.setString(4,user.getSex());
        pstmt2.setString(5,user.getPhone());
        return pstmt2.executeUpdate();
    }


    public ResultSet list(Connection con, Users user)throws Exception{
        StringBuffer sb=new StringBuffer("select * from user where role = 1");
        if(!ToolUtil.isEmpty(user.getUserName())){
            sb.append(" and username like '%"+user.getUserName()+"%'");
        }
        PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int update(Connection con, Users user)throws Exception{
        String sql="update user set username=?,password=?,sex=?,phone=? where id=?";
        PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getSex());
        pstmt.setString(4, user.getPhone());
        pstmt.setInt(5, user.getUserId());
        return pstmt.executeUpdate();
    }
}

