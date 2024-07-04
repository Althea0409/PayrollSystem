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

public class DptSearch extends JInternalFrame {

    private final DbUtil dbUtil; // 声明 DbUtil 实例
    private final JTextArea resultArea; // 用于显示查询结果
    private final JComboBox<String> criteriaComboBox; // 查询条件下拉菜单
    private final JTextField inputField; // 输入框

    public DptSearch() {
        super("部门信息查询", true, true, true, true);

        // 创建顶部面板
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

        JLabel cx = new JLabel("查询类型:");
        cx.setFont(new Font("SansSerif", Font.BOLD, 18));

        // 查询条件下拉菜单
        String[] criteriaOptions = {"部门编号", "部门名称"};
        criteriaComboBox = new JComboBox<>(criteriaOptions);
        criteriaComboBox.setFont(new Font("SansSerif", Font.BOLD, 18));

        // 输入框
        JLabel inputLabel = new JLabel("输入:");
        inputLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        inputField = new JTextField(10);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JButton searchButton = new JButton("查询");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 18));

        topPanel.add(cx);
        topPanel.add(criteriaComboBox);
        topPanel.add(inputLabel);
        topPanel.add(inputField);
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
                String input = inputField.getText();
                resultArea.setText(""); // 清空之前的查询结果

                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(DptSearch.this, "请输入查询内容",
                            "输入错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    // 获取数据库连接
                    con = dbUtil.getConnection();

                    String sql;
                    if (criteriaComboBox.getSelectedIndex() == 0) {
                        sql = "SELECT * FROM department WHERE DptId=?";
                    } else {
                        sql = "SELECT * FROM department WHERE DptName=?";
                    }

                    pstmt = con.prepareStatement(sql);

                    if (criteriaComboBox.getSelectedIndex() == 0) {
                        pstmt.setInt(1, Integer.parseInt(input));
                    } else {
                        pstmt.setString(1, input);
                    }

                    rs = pstmt.executeQuery();

                    // 处理查询结果
                    if (rs.next()) {
                        int dptId = rs.getInt("DptId");
                        String dptName = rs.getString("DptName");
                        String dptHead = rs.getString("DptHead");
                        int dptMembers = rs.getInt("DptMembers");

                        // 显示部门信息
                        resultArea.append("部门编号: " + dptId + "\n");
                        resultArea.append("部门名称: " + dptName + "\n");
                        resultArea.append("部门负责人: " + dptHead + "\n");
                        resultArea.append("部门人数: " + dptMembers + "\n");
                    } else {
                        resultArea.append("没有找到匹配的部门信息");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DptSearch.this, "数据库操作出错：" + ex.getMessage(),
                            "错误", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DptSearch.this, "操作出现异常：" + ex.getMessage(),
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
        this.setSize(600, 500); // 设置窗口大小
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
    }
}
