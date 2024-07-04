package com.lsu.view;

import com.lsu.dao.PayDao;
import com.lsu.model.Pay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PayAlter extends JInternalFrame {
    private final PayDao payDao;

    private JTable payTable;
    private DefaultTableModel tableModel;

    public PayAlter() {
        super("工资信息修改", true, true, true, true);
        payDao = new PayDao();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        initializeUI();

        try {
            refreshPayTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 加载全部表格
    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 工资信息表格
        String[] columnNames = {"员工编号", "基本工资", "岗位工资", "住房补贴", "津贴", "工会会费", "水电费", "住房公积金", "养老保险", "奖惩"};
        tableModel = new DefaultTableModel(columnNames, 0);
        payTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(payTable);
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
            int selectedRow = payTable.getSelectedRow();
            if (selectedRow != -1) {
                Object value = payTable.getValueAt(selectedRow, 0);
                if (value != null) {
                    int staffId = (int) value;
                    Pay pay = fetchPayFromTable(selectedRow);
                    new UpdatePay(this, pay, payDao).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "选中的单元格为空，请选择正确的行。");
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要修改的工资信息行。");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = payTable.getSelectedRow();
            if (selectedRow != -1) {
                Object value = payTable.getValueAt(selectedRow, 0);
                if (value != null) {
                    int staffId = (int) value;
                    int confirm = JOptionPane.showConfirmDialog(this, "确定要删除该工资信息吗？");
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            int deleted = payDao.deletePay(staffId);
                            if (deleted == 1) {
                                JOptionPane.showMessageDialog(this, "工资信息删除成功。");
                                refreshPayTable();
                            } else {
                                JOptionPane.showMessageDialog(this, "删除工资信息失败。");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "选中的单元格为空，请选择正确的行。");
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的工资信息行。");
            }
        });

        setContentPane(mainPanel);
    }

    // 刷新表格
    void refreshPayTable() throws Exception {
        List<Pay> payList = payDao.getAllPay();
        if (payList == null || payList.isEmpty()) {
            System.out.println("No pay records found.");
            return;
        }
        tableModel.setRowCount(0);
        for (Pay pay : payList) {
            Object[] rowData = pay.toObjectArray();
            tableModel.addRow(rowData);
        }
        System.out.println("Pay records loaded: " + payList.size());
    }

    // 从表格中获取工资信息
    private Pay fetchPayFromTable(int row) {
        int staffId = (int) payTable.getValueAt(row, 0);
        double basicPay = (double) payTable.getValueAt(row, 1);
        double positionPay = (double) payTable.getValueAt(row, 2);
        double houseAllowance = (double) payTable.getValueAt(row, 3);
        double allowance = (double) payTable.getValueAt(row, 4);
        double unionFee = (double) payTable.getValueAt(row, 5);
        double weFee = (double) payTable.getValueAt(row, 6);
        double houseFund = (double) payTable.getValueAt(row, 7);
        double pension = (double) payTable.getValueAt(row, 8);
        double bonusPenalty = (double) payTable.getValueAt(row, 9);

        return new Pay(staffId, basicPay, positionPay, houseAllowance, allowance, unionFee, weFee, houseFund, pension, bonusPenalty);
    }
}
