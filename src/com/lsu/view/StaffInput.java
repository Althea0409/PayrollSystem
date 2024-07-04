package com.lsu.view;

import com.lsu.utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffInput extends JInternalFrame {

    private final JTextField staffIdField;
    private final JTextField nameField;
    private final JComboBox<String> genderComboBox;
    private final JTextField birthDateField;
    private final JTextField joinDateField;
    private final JTextField dptIdField;
    private final JTextField dptNameField;
    private final JTextField positionField;
    private final JTextField titleField;
    private final JTextField polStatusField;
    private final JTextField marStatusField;

    private final DbUtil dbUtil;

    public StaffInput() {
        super("员工信息输入", true, true, true, true);

        dbUtil = new DbUtil(); // 实例化 DbUtil 对象

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 员工编号
        gbc.fill = GridBagConstraints.HORIZONTAL; // 使用水平填充
        JLabel staffIdLabel = new JLabel("员工编号:");
        staffIdLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(staffIdLabel, gbc);

        staffIdField = new JTextField(20);
        staffIdField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(staffIdField, gbc);

        // 姓名
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("姓名:");
        nameLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        // 性别
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel genderLabel = new JLabel("性别:");
        genderLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(genderLabel, gbc);

        genderComboBox = new JComboBox<>(new String[]{"男", "女"});
        genderComboBox.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(genderComboBox, gbc);

        // 出生日期
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel birthDateLabel = new JLabel("出生日期:");
        birthDateLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(birthDateLabel, gbc);

        birthDateField = new JTextField(20);
        birthDateField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(birthDateField, gbc);

        // 参加工作时间
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel joinDateLabel = new JLabel("参加工作时间:");
        joinDateLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(joinDateLabel, gbc);

        joinDateField = new JTextField(20);
        joinDateField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(joinDateField, gbc);

        // 部门编号
        gbc.gridy++;
        gbc.gridx = 0;
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

        // 职务
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel positionLabel = new JLabel("职务:");
        positionLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(positionLabel, gbc);

        positionField = new JTextField(20);
        positionField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(positionField, gbc);

        // 职称
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel titleLabel = new JLabel("职称:");
        titleLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(titleLabel, gbc);

        titleField = new JTextField(20);
        titleField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(titleField, gbc);

        // 政治面貌
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel polStatusLabel = new JLabel("政治面貌:");
        polStatusLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(polStatusLabel, gbc);

        polStatusField = new JTextField(20);
        polStatusField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(polStatusField, gbc);

        // 婚姻状况
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel marStatusLabel = new JLabel("婚姻状况:");
        marStatusLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(marStatusLabel, gbc);

        marStatusField = new JTextField(20);
        marStatusField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(marStatusField, gbc);

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
                saveEmployeeInfo();
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

    // 保存员工信息到数据库
    private void saveEmployeeInfo() {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbUtil.getConnection(); // 使用 DbUtil 获取连接
            String sql = "INSERT INTO staff (StaffID, Name, Gender, BirthDate, JoinDate, DptID, DptName, Position, Title, PolStatus, MarStatus) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(staffIdField.getText()));
            pstmt.setString(2, nameField.getText());
            pstmt.setString(3, (String) genderComboBox.getSelectedItem());
            pstmt.setString(4, birthDateField.getText());
            pstmt.setString(5, joinDateField.getText());
            pstmt.setInt(6, Integer.parseInt(dptIdField.getText()));
            pstmt.setString(7, dptNameField.getText());
            pstmt.setString(8, positionField.getText());
            pstmt.setString(9, titleField.getText());
            pstmt.setString(10, polStatusField.getText());
            pstmt.setString(11, marStatusField.getText());
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "员工信息保存成功！");
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
        staffIdField.setText("");
        nameField.setText("");
        genderComboBox.setSelectedIndex(0);
        birthDateField.setText("");
        joinDateField.setText("");
        dptIdField.setText("");
        dptNameField.setText("");
        positionField.setText("");
        titleField.setText("");
        polStatusField.setText("");
        marStatusField.setText("");
    }

}
