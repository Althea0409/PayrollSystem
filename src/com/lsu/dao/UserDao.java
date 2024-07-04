package com.lsu.dao;

import com.lsu.model.Users;
import com.lsu.utils.ToolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserDao {

    // 用户登录
    public Users login(Connection con, Users user)throws Exception {
        Users resultUser = null;
        String sql = "select * from users where UserName=? and Password=? and Role = ?";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getRole());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            resultUser = new Users();
            resultUser.setUserId(rs.getInt("UserId"));
            resultUser.setUserName(rs.getString("UserName"));
            resultUser.setSex(rs.getString("Sex"));
            resultUser.setPhone(rs.getString("Phone"));
        }
        return resultUser;
    }

    // 用户注册
    public int addUser(Connection con, Users user) throws Exception{
        //查询注册用户名是否存在
        String sql = "select * from User where UserName=? ";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return 2;
        }

        sql="insert into users (UserName,Password,Role,Sex,Phone) values (?,?,?,?,?)";
        PreparedStatement pstmt2=(PreparedStatement) con.prepareStatement(sql);
        pstmt2.setString(1, user.getUserName());
        pstmt2.setString(2, user.getPassword());
        pstmt2.setInt(3, user.getRole());
        pstmt2.setString(4,user.getSex());
        pstmt2.setString(5,user.getPhone());
        return pstmt2.executeUpdate();
    }

    // 用户列表
    public ResultSet list(Connection con, Users user)throws Exception{
        StringBuffer sb=new StringBuffer("select * from users where Role = 1");
        if(!ToolUtil.isEmpty(user.getUserName())){
            sb.append(" and username like '%"+user.getUserName()+"%'");
        }
        PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    // 删除用户
    public int update(Connection con, Users user)throws Exception{
        String sql="update users set UserName=?,Password=?,Sex=?,Phone=? where UserId=?";
        PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getSex());
        pstmt.setString(4, user.getPhone());
        pstmt.setInt(5, user.getUserId());
        return pstmt.executeUpdate();
    }
}

