package com.lsu.view;

import com.lsu.dao.DptDao;
import com.lsu.model.Dpt;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UpdateDpt extends JDialog {
    private final DptDao dptDao;
    private final Dpt dpt;

    private JTextField dptNameField;
    private JTextField dptHeadField;
    private JTextField dptMembersField;

    public UpdateDpt(DptAlter parent, Dpt dpt, DptDao dptDao) {
        super();
        this.dpt = dpt;
        this.dptDao = dptDao;

        setTitle("修改部门信息");
        setSize(400, 300);
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 部门名称
        gbc.gridx = 0;
        gbc.gridy = 0;
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
        JLabel dptMembersLabel = new JLabel("部门人数:");
        dptMembersLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(dptMembersLabel, gbc);

        dptMembersField = new JTextField(20);
        dptMembersField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(dptMembersField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 修改按钮
        JButton updateButton = new JButton("修改");
        updateButton.setFont(new Font("幼圆", Font.BOLD, 18));
        updateButton.setPreferredSize(new Dimension(100, 35));
        updateButton.addActionListener(e -> {
            updateDpt();
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

    // 根据传入的Dpt对象，填充表单字段
    private void populateFields() {
        dptNameField.setText(dpt.getDptName());
        dptHeadField.setText(dpt.getDptHead());
        dptMembersField.setText(String.valueOf(dpt.getDptMembers()));
    }

    // 更新部门信息
    private void updateDpt() {
        dpt.setDptName(dptNameField.getText());
        dpt.setDptHead(dptHeadField.getText());
        dpt.setDptMembers(Integer.parseInt(dptMembersField.getText()));

        try {
            int rowsAffected = dptDao.updateDpt(dpt);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "部门信息修改成功。");
                if (getParent() instanceof DptAlter) {
                    ((DptAlter) getParent()).refreshDptTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "修改部门信息失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "修改部门信息失败：" + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "请输入有效的部门人数。");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
