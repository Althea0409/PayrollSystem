package com.lsu.view;

import com.lsu.dao.StaffDao;
import com.lsu.model.Staff;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UpdateStaff extends JDialog {
    private final StaffDao staffDao;
    private final Staff staff;

    private JTextField nameField;
    private JTextField genderField;
    private JTextField birthDateField;
    private JTextField joinDateField;
    private JTextField dptIdField;
    private JTextField dptNameField;
    private JTextField positionField;
    private JTextField titleField;
    private JTextField polStatusField;
    private JTextField marStatusField;

    public UpdateStaff(StaffAlter parent, Staff staff, StaffDao staffDao) {
        super();
        this.staff = staff;
        this.staffDao = staffDao;

        setTitle("修改员工信息");
        setSize(550, 550);
        setLocationRelativeTo(parent);

        initializeUI();
        populateFields();
    }

    // 初始化界面
    private void initializeUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 使用水平填充

        // 姓名
        gbc.gridx = 0;
        gbc.gridy = 0;
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

        genderField = new JTextField(20);
        genderField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(genderField, gbc);

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

        // 入职日期
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel joinDateLabel = new JLabel("入职日期:");
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

        // 修改按钮
        JButton updateButton = new JButton("修改");
        updateButton.setFont(new Font("幼圆", Font.BOLD, 18));
        updateButton.setPreferredSize(new Dimension(100, 35));
        updateButton.addActionListener(e -> {
            updateStaff();
            dispose();
        });
        buttonPanel.add(updateButton);

        // 取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("幼圆", Font.BOLD, 18));
        cancelButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        // 添加按钮面板
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        setContentPane(mainPanel);
    }

    // 根据传入的Staff对象，填充表单字段
    private void populateFields() {
        nameField.setText(staff.getName());
        genderField.setText(staff.getGender());
        birthDateField.setText(staff.getBirthDate());
        joinDateField.setText(staff.getJoinDate());
        dptIdField.setText(String.valueOf(staff.getDptId()));
        dptNameField.setText(staff.getDptName());
        positionField.setText(staff.getPosition());
        titleField.setText(staff.getTitle());
        polStatusField.setText(staff.getPolStatus());
        marStatusField.setText(staff.getMarStatus());
    }

    // 更新员工信息
    private void updateStaff() {
        staff.setName(nameField.getText());
        staff.setGender(genderField.getText());
        staff.setBirthDate(birthDateField.getText());
        staff.setJoinDate(joinDateField.getText());
        staff.setDptId(Integer.parseInt(dptIdField.getText()));
        staff.setDptName(dptNameField.getText());
        staff.setPosition(positionField.getText());
        staff.setTitle(titleField.getText());
        staff.setPolStatus(polStatusField.getText());
        staff.setMarStatus(marStatusField.getText());

        try {
            int rowsAffected = staffDao.updateStaff(staff);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "员工信息修改成功。");
                if (getParent() instanceof StaffAlter) {
                    ((StaffAlter) getParent()).refreshStaffTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "修改员工信息失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "修改员工信息失败：" + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "部门编号必须是整数。");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
