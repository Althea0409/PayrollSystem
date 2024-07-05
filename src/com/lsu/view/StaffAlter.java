package com.lsu.view;

import com.lsu.dao.StaffDao;
import com.lsu.model.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StaffAlter extends JInternalFrame {
    private final StaffDao staffDao;

    private JTable staffTable;
    private DefaultTableModel tableModel;

    // 创建员工信息修改窗口
    public StaffAlter() {
        super("员工信息修改", true, true, true, true);
        staffDao = new StaffDao();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        initializeUI();

        try {
            refreshStaffTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 加载全部表格
    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 员工信息表格
        String[] columnNames = {"员工编号", "姓名", "性别", "出生日期", "入职日期", "部门编号", "部门名称", "职务", "职称", "政治面貌", "婚姻状况"};
        tableModel = new DefaultTableModel(columnNames, 0);
        staffTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(staffTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton updateButton = new JButton("修 改");
        JButton deleteButton = new JButton("删 除");

        // 设置按钮大小
        Dimension buttonSize = new Dimension(100, 40);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow != -1) {
                int staffId = (int) staffTable.getValueAt(selectedRow, 0);
                Staff staff = fetchStaffFromTable(selectedRow);
                new UpdateStaff(this, staff, staffDao).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "请选择要修改的员工信息行。");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow != -1) {
                int staffId = (int) staffTable.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "确定要删除该员工信息吗？");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowsAffected = staffDao.deleteStaff(staffId);
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "员工信息删除成功。");
                            refreshStaffTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "删除员工信息失败。");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的员工信息行。");
            }
        });

        setContentPane(mainPanel);
    }

    // 加载并显示所有员工信息
    void refreshStaffTable() throws Exception {
        List<Staff> staffList = staffDao.getAllStaff();
        tableModel.setRowCount(0);
        for (Staff staff : staffList) {
            Object[] rowData = staff.toObjectArray();
            tableModel.addRow(rowData);
        }
    }

    // 从表格中获取员工信息
    private Staff fetchStaffFromTable(int row) {
        int staffId = (int) staffTable.getValueAt(row, 0);
        String name = (String) staffTable.getValueAt(row, 1);
        String gender = (String) staffTable.getValueAt(row, 2);
        String birthDate = (String) staffTable.getValueAt(row, 3);
        String joinDate = (String) staffTable.getValueAt(row, 4);
        int dptId = (int) staffTable.getValueAt(row, 5);
        String dptName = (String) staffTable.getValueAt(row, 6);
        String position = (String) staffTable.getValueAt(row, 7);
        String title = (String) staffTable.getValueAt(row, 8);
        String polStatus = (String) staffTable.getValueAt(row, 9);
        String marStatus = (String) staffTable.getValueAt(row, 10);

        return new Staff(staffId, name, gender, birthDate, joinDate, dptId, dptName, position, title, polStatus, marStatus);
    }
}
