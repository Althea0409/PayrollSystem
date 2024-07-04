package com.lsu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtil {
    private final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private final String dbUrl = "jdbc:mysql://localhost:3306/payrollsystem?characterEncoding=utf-8";
    private final String dbUserName = "root";
    private final String dbPassword = "123456";

    public Connection getConnection()throws Exception{
        Class.forName(dbDriver);
        Connection con = (Connection) DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }

    public void closeCon(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
    }

    public void closeCon (Connection con)throws Exception {
        if(con!=null){
            con.close();
        }
    }


}
