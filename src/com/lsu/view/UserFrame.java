package com.lsu.view;

import com.lsu.utils.DbUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class UserFrame extends JFrame {
    private final DefaultTableModel staffTableModel;
    private final DefaultTableModel payTableModel;
    private final DbUtil dbUtil = new DbUtil();

    public UserFrame() {
        setTitle("员工工资管理系统 - 员工界面");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 欢迎标签
        JLabel welcomeLabel = new JLabel("欢迎您", JLabel.CENTER);
        welcomeLabel.setFont(new Font("宋体", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.NORTH);

        // 设置顶部边距
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        welcomeLabel.setBorder(emptyBorder);

        // 员工信息表格初始化
        String[] staffTableHeader = {"员工编号", "姓名", "性别", "出生日期", "参加工作时间", "部门编号", "部门名称", "职务", "职称", "政治面貌", "婚姻状况"};
        staffTableModel = new DefaultTableModel(null, staffTableHeader);
        JTable staffTable = new JTable(staffTableModel);
        JScrollPane staffScrollPane = new JScrollPane(staffTable);
        staffScrollPane.setPreferredSize(new Dimension(600, 300));
        // 加入淡蓝色边框和标题
        addBorderWithTitle(staffScrollPane, "基本信息");

        // 工资信息表格初始化
        String[] payTableHeader = {"员工编号", "基本工资", "岗位工资", "住房补贴", "津贴", "工会会费", "水电费", "住房公积金", "养老保险", "奖惩", "应发金额", "应扣金额", "实发金额"};
        payTableModel = new DefaultTableModel(null, payTableHeader);
        JTable payTable = new JTable(payTableModel);
        JScrollPane payScrollPane = new JScrollPane(payTable);
        payScrollPane.setPreferredSize(new Dimension(900, 200));
        // 加入淡蓝色边框和标题
        addBorderWithTitle(payScrollPane, "工资信息");

        // 添加表格到界面
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(staffScrollPane);
        panel.add(payScrollPane);
        add(panel, BorderLayout.CENTER);

        // 加载数据到表格
        loadStaffData();
        loadPayData();

        setVisible(true);
    }

    // 加载员工数据到表格
    private void loadStaffData() {
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM staff";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(rs.getInt("StaffId"));
                rowData.add(rs.getString("Name"));
                rowData.add(rs.getString("Gender"));
                rowData.add(rs.getDate("BirthDate"));
                rowData.add(rs.getDate("JoinDate"));
                rowData.add(rs.getInt("DptId"));
                rowData.add(rs.getString("DptName"));
                rowData.add(rs.getString("Position"));
                rowData.add(rs.getString("Title"));
                rowData.add(rs.getString("PolStatus"));
                rowData.add(rs.getString("MarStatus"));
                staffTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 加载工资数据到表格
    private void loadPayData() {
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM pay";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(rs.getInt("StaffId"));
                rowData.add(rs.getDouble("BasicPay"));
                rowData.add(rs.getDouble("PositionPay"));
                rowData.add(rs.getDouble("HouseAllowance"));
                rowData.add(rs.getDouble("Allowance"));
                rowData.add(rs.getDouble("UnionFee"));
                rowData.add(rs.getDouble("WEFee"));
                rowData.add(rs.getDouble("HouseFund"));
                rowData.add(rs.getDouble("Pension"));
                rowData.add(rs.getDouble("BonusPenalty"));
                // 计算应发金额、应扣金额、实发金额
                double totalIncome = rs.getDouble("BasicPay") + rs.getDouble("PositionPay") + rs.getDouble("HouseAllowance") +
                        rs.getDouble("Allowance") - rs.getDouble("UnionFee");
                double totalDeduction = rs.getDouble("WEFee") + rs.getDouble("HouseFund") + rs.getDouble("Pension");
                double netIncome = totalIncome - totalDeduction;

                rowData.add(totalIncome);
                rowData.add(totalDeduction);
                rowData.add(netIncome);
                payTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 添加淡蓝色边框和标题
    private void addBorderWithTitle(JScrollPane scrollPane, String title) {
        Color borderColor = new Color(0, 0, 255, 100);
        Border blueBorder = BorderFactory.createLineBorder(borderColor);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                blueBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 16));
        scrollPane.setColumnHeaderView(titleLabel);
    }

    public static void main(String[] args) {
        // 设置界面风格为系统默认风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 启动工资管理系统界面
        new UserFrame();
    }
}
