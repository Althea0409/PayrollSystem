package com.lsu.view;

import com.lsu.utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DptInput extends JInternalFrame {

    private final JTextField dptIdField;
    private final JTextField dptNameField;
    private final JTextField dptHeadField;
    private final JTextField dptNumField;

    private final DbUtil dbUtil;

    public DptInput() {
        super("部门信息输入", true, true, true, true);

        dbUtil = new DbUtil(); // 实例化 DbUtil 对象

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 部门编号
        gbc.fill = GridBagConstraints.HORIZONTAL; // 使用水平填充
        JLabel dptIdLabel = new JLabel("部门编号:");
        dptIdLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(dptIdLabel, gbc);

        dptIdField = new JTextField(20);
        dptIdField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(dptIdField, gbc);

        // 部门名称
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel dptNameLabel = new JLabel("部门名称:");
        dptNameLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(dptNameLabel, gbc);

        dptNameField = new JTextField(20);
        dptNameField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(dptNameField, gbc);

        // 部门负责人
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel dptHeadLabel = new JLabel("部门负责人:");
        dptHeadLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(dptHeadLabel, gbc);

        dptHeadField = new JTextField(20);
        dptHeadField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(dptHeadField, gbc);

        // 部门人数
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel dptStaffCountLabel = new JLabel("部门人数:");
        dptStaffCountLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(dptStaffCountLabel, gbc);

        dptNumField = new JTextField(20);
        dptNumField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(dptNumField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // 流式布局，居中对齐

        // 重置按钮
        JButton resetButton = new JButton("重置");
        resetButton.setFont(new Font("幼圆", Font.BOLD, 18));
        resetButton.setPreferredSize(new Dimension(100, 35));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        buttonPanel.add(resetButton);

        // 保存按钮
        JButton saveButton = new JButton("保存");
        saveButton.setFont(new Font("幼圆", Font.BOLD, 18));
        saveButton.setPreferredSize(new Dimension(100, 35));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDepartmentInfo();
            }
        });
        buttonPanel.add(saveButton);

        // 添加按钮面板
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        pack(); // 调整内部窗口的大小以适应内容
        setVisible(true);
    }

    // 保存部门信息到数据库
    private void saveDepartmentInfo() {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection(); // 使用 DbUtil 获取连接
            String sql = "INSERT INTO department (DptId, DptName, DptHead, DptNum) " +
                    "VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(dptIdField.getText()));
            pstmt.setString(2, dptNameField.getText());
            pstmt.setString(3, dptHeadField.getText());
            pstmt.setInt(4, Integer.parseInt(dptNumField.getText()));
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "部门信息保存成功！");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) dbUtil.closeCon(con); // 使用 DbUtil 关闭连接
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // 重置所有字段
    private void resetFields() {
        dptIdField.setText("");
        dptNameField.setText("");
        dptHeadField.setText("");
        dptNumField.setText("");
    }

}
