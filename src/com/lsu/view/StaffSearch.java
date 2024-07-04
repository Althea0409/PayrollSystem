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

public class StaffSearch extends JInternalFrame {

    private DbUtil dbUtil; // 声明 DbUtil 实例
    private JTextArea resultArea; // 用于显示查询结果

    public StaffSearch() {
        super("员工信息查询", true, true, true, true);

        // 创建顶部面板
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

        JLabel l1 = new JLabel("查询类型:");
        l1.setFont(new Font("SansSerif", Font.BOLD, 18));

        String[] searchTypes = {"员工编号", "员工姓名"};
        JComboBox<String> searchTypeComboBox = new JComboBox<>(searchTypes);
        searchTypeComboBox.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JTextField searchField = new JTextField(10);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JButton searchButton = new JButton("查询");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 18));

        topPanel.add(l1);
        topPanel.add(searchTypeComboBox);
        topPanel.add(searchField);
        topPanel.add(searchButton);

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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchType = (String) searchTypeComboBox.getSelectedItem();
                String searchValue = searchField.getText();
                resultArea.setText(""); // 清空之前的查询结果

                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    // 获取数据库连接
                    con = dbUtil.getConnection();
                    String sql = "";
                    if ("员工编号".equals(searchType)) {
                        sql = "SELECT * FROM staff WHERE StaffID=?";
                    } else if ("员工姓名".equals(searchType)) {
                        sql = "SELECT * FROM staff WHERE Name=?";
                    }

                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, searchValue);
                    rs = pstmt.executeQuery();

                    // 处理查询结果
                    if (rs.next()) {
                        String staffId = rs.getString("StaffID");
                        String staffName = rs.getString("Name");
                        String gender = rs.getString("Gender");
                        String birthDate = rs.getString("BirthDate");
                        String joinDate = rs.getString("JoinDate");
                        String dptName = rs.getString("DptName");
                        String position = rs.getString("Position");
                        String title = rs.getString("Title");
                        String polStatus = rs.getString("PolStatus");
                        String marStatus = rs.getString("MarStatus");

                        // 显示员工基本信息
                        resultArea.append("员工编号: " + staffId + "\n");
                        resultArea.append("姓名: " + staffName + "\n");
                        resultArea.append("性别: " + gender + "\n");
                        resultArea.append("出生年月: " + birthDate + "\n");
                        resultArea.append("参加工作时间: " + joinDate + "\n");
                        resultArea.append("所属部门: " + dptName + "\n");
                        resultArea.append("职务: " + position + "\n");
                        resultArea.append("职称: " + title + "\n");
                        resultArea.append("政治面貌: " + polStatus + "\n");
                        resultArea.append("婚姻状况: " + marStatus + "\n");

                    } else {
                        resultArea.append("没有找到相关员工信息");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StaffSearch.this, "数据库操作出错：" + ex.getMessage(),
                            "错误", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StaffSearch.this, "操作出现异常：" + ex.getMessage(),
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
