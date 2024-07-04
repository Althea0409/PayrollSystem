package com.lsu.view;

import com.lsu.dao.DptDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DptInput extends JInternalFrame {

    private final JTextField dptIdField;
    private final JTextField dptNameField;
    private final JTextField dptHeadField;
    private final JTextField dptNumField;

    private final DptDao dptDao;

    public DptInput() {
        super("部门信息输入", true, true, true, true);

        dptDao = new DptDao(); // 实例化 DptDao 对象

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
        try {
            int dptId = Integer.parseInt(dptIdField.getText());
            String dptName = dptNameField.getText();
            String dptHead = dptHeadField.getText();
            int dptNum = Integer.parseInt(dptNumField.getText());

            boolean isSaved = dptDao.saveDepartmentInfo(dptId, dptName, dptHead, dptNum);
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "部门信息保存成功！");
            } else {
                JOptionPane.showMessageDialog(this, "部门信息保存失败！");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的数字！", "输入错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存部门信息时发生错误！", "错误", JOptionPane.ERROR_MESSAGE);
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
