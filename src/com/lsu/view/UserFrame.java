package com.lsu.view;

import com.lsu.utils.DbUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class UserFrame extends JFrame {

    private final DefaultTableModel staffTableModel;
    private final DefaultTableModel payTableModel;
    private final DbUtil dbUtil = new DbUtil();
    private JLabel welcomeLabel;

    public UserFrame() {
        setTitle("员工工资管理系统 - 员工界面");
        setSize(1000, 335);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu returnLoginMenu = new JMenu("返回登录");
        JMenu exitMenu = new JMenu("退出系统");
        menuBar.add(returnLoginMenu);
        menuBar.add(exitMenu);

        // 返回登录
        returnLoginMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 返回登录逻辑
                dispose(); // 关闭当前窗口
                new LoginFrame(); // 打开登录界面
            }
        });

        // 退出系统
        exitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 退出系统逻辑
                String message = "确定退出系统吗？";
                int option = JOptionPane.showConfirmDialog(null, message, "确认退出", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // 退出程序
                }
            }
        });

        // 欢迎标签
        welcomeLabel = new JLabel("欢迎您，小王", JLabel.CENTER);
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

        // 工资信息表格初始化
        String[] payTableHeader = {"员工编号", "基本工资", "岗位工资", "住房补贴", "津贴", "工会会费", "水电费", "住房公积金", "养老保险", "奖惩", "应发金额", "应扣金额", "实发金额"};
        payTableModel = new DefaultTableModel(null, payTableHeader);
        JTable payTable = new JTable(payTableModel);
        JScrollPane payScrollPane = new JScrollPane(payTable);
        payScrollPane.setPreferredSize(new Dimension(900, 200));

        // 添加表格到界面
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(createTitledPanel(staffScrollPane, "基本信息"));
        panel.add(createTitledPanel(payScrollPane, "工资信息"));
        add(panel, BorderLayout.CENTER);

        // 加载数据到表格
        loadStaffData();
        loadPayData();

        setVisible(true);
    }

    // 创建带有标题的面板
    private JPanel createTitledPanel(JScrollPane scrollPane, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);

        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 在标题和表格之间增加额外的边距
        Border margin = BorderFactory.createEmptyBorder(0, 0, 10, 0);
        titleLabel.setBorder(margin);

        Color borderColor = new Color(0, 0, 255, 100);
        Border blueBorder = BorderFactory.createLineBorder(borderColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                blueBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        return panel;
    }

    // 加载员工数据到表格
    private void loadStaffData() {
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            String sql = "SELECT * FROM staff";
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(rs.getInt("StaffID"));
                rowData.add(rs.getString("Name"));
                rowData.add(rs.getString("Gender"));
                rowData.add(rs.getDate("BirthDate"));
                rowData.add(rs.getDate("JoinDate"));
                rowData.add(rs.getInt("DptID"));
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
            if (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(rs.getInt("StaffID"));
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
                        rs.getDouble("Allowance") + rs.getDouble("BonusPenalty");
                double totalDeduction = rs.getDouble("UnionFee") + rs.getDouble("WEFee") + rs.getDouble("HouseFund") + rs.getDouble("Pension");
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

    public static void main(String[] args) {
        // 设置界面风格为系统默认风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new UserFrame();
    }

}
