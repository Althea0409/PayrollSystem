package com.lsu.view;

import com.lsu.dao.DptDao;
import com.lsu.model.Dpt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DptAlter extends JInternalFrame {
    private final DptDao dptDao;

    private JTable dptTable;
    private DefaultTableModel tableModel;

    public DptAlter() {
        super("部门信息修改", true, true, true, true);
        dptDao = new DptDao();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        initializeUI();

        try {
            refreshDptTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 加载全部表格
    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 部门信息表格
        String[] columnNames = {"部门编号", "部门名称", "部门负责人", "部门人数"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dptTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dptTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10)); // 增大按钮之间的左右间距
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
            int selectedRow = dptTable.getSelectedRow();
            if (selectedRow != -1) {
                int dptId = (int) dptTable.getValueAt(selectedRow, 0);
                Dpt dpt = fetchDptFromTable(selectedRow);
                new UpdateDpt(this, dpt, dptDao).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "请选择要修改的部门信息行。");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = dptTable.getSelectedRow();
            if (selectedRow != -1) {
                int dptId = (int) dptTable.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "确定要删除该部门信息吗？");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowsAffected = dptDao.deleteDpt(dptId);
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "部门信息删除成功。");
                            refreshDptTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "删除部门信息失败。");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的部门信息行。");
            }
        });

        setContentPane(mainPanel);
    }

    // 刷新表格
    void refreshDptTable() throws Exception {
        List<Dpt> dptList = dptDao.getAllDpt();
        tableModel.setRowCount(0);
        for (Dpt dpt : dptList) {
            Object[] rowData = dpt.toObjectArray();
            tableModel.addRow(rowData);
        }
    }

    // 从表格中获取部门信息
    private Dpt fetchDptFromTable(int row) {
        int dptId = (int) dptTable.getValueAt(row, 0);
        String dptName = (String) dptTable.getValueAt(row, 1);
        String dptHead = (String) dptTable.getValueAt(row, 2);
        int dptMembers = (int) dptTable.getValueAt(row, 3);

        return new Dpt(dptId, dptName, dptHead, dptMembers);
    }
}
