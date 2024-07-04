package com.lsu.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.lsu.utils.DbUtil;

public class PaySearch extends JInternalFrame {

    private DbUtil dbUtil; // 声明 DbUtil 实例
    private JTextArea resultArea; // 用于显示查询结果
    private JTextField inputField; // 输入框，用于员工编号输入

    public PaySearch() {
        super("工资信息查询", true, true, true, true);

        // 创建顶部面板
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));
        JLabel l1 = new JLabel("员工编号:");
        l1.setFont(new Font("SansSerif", Font.BOLD, 18));
        inputField = new JTextField(10);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JButton b1 = new JButton("查询");
        b1.setFont(new Font("SansSerif", Font.BOLD, 18));
        topPanel.add(l1);
        topPanel.add(inputField);
        topPanel.add(b1);

        // 创建结果显示区域
        resultArea = new JTextArea(10, 30); // 设置为10行30列
        resultArea.setEditable(false); // 设置 JTextArea 不可编辑
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 18)); // 设置字体样式
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 添加边框

        // 中心面板
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(resultArea, gbc);

        // 设置整体布局
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH); // 顶部面板
        this.add(centerPanel, BorderLayout.CENTER); // 中部面板

        // 初始化 DbUtil 实例
        dbUtil = new DbUtil();

        // 查询按钮事件处理
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                resultArea.setText(""); // 清空之前的查询结果

                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    // 获取数据库连接
                    con = dbUtil.getConnection();

                    // 构建SQL查询语句
                    String sql = "SELECT e.StaffID, e.Name, " +
                            "(p.BasicPay + p.PositionPay + p.HouseAllowance + p.Allowance + p.BonusPenalty) AS TotalPay, " +
                            "(p.UnionFee + p.WEFee + p.HouseFund + p.Pension) AS TotalDeductions, " +
                            "(p.BasicPay + p.PositionPay + p.HouseAllowance + p.Allowance + p.BonusPenalty - p.UnionFee - p.WEFee - p.HouseFund - p.Pension) AS NetPay " +
                            "FROM staff e INNER JOIN pay p ON e.StaffID = p.StaffID " +
                            "WHERE e.StaffID=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, input);

                    rs = pstmt.executeQuery();

                    // 处理查询结果
                    if (rs.next()) {
                        String staffID = rs.getString("StaffID");
                        String name = rs.getString("Name");
                        double totalPay = rs.getDouble("TotalPay");
                        double totalDeductions = rs.getDouble("TotalDeductions");
                        double netPay = rs.getDouble("NetPay");

                        // 显示员工工资信息
                        resultArea.append("员工编号: " + staffID + "\n");
                        resultArea.append("姓名: " + name + "\n");
                        resultArea.append("每月应发金额: " + totalPay + "\n");
                        resultArea.append("每月应扣金额: " + totalDeductions + "\n");
                        resultArea.append("每月实发金额: " + netPay + "\n");

                        // 显示每年的金额信息
                        resultArea.append("每年实发金额: " + netPay * 12 + "\n\n");
                    } else {
                        resultArea.setText("没有找到编号为 " + input + " 的员工工资信息");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PaySearch.this, "数据库操作出错：" + ex.getMessage(),
                            "错误", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PaySearch.this, "操作出现异常：" + ex.getMessage(),
                            "异常", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // 关闭数据库连接和资源
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        dbUtil.closeCon(con);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // 设置窗口属性
        this.setVisible(true);
        this.setSize(500, 600); // 设置窗口大小
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
    }

}
