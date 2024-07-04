package com.lsu.view;

import com.lsu.dao.PayDao;
import com.lsu.model.Pay;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UpdatePay extends JDialog {
    private final PayDao payDao;
    private final Pay pay;

    private JTextField basicPayField;
    private JTextField positionPayField;
    private JTextField houseAllowanceField;
    private JTextField allowanceField;
    private JTextField unionFeeField;
    private JTextField weFeeField;
    private JTextField houseFundField;
    private JTextField pensionField;
    private JTextField bonusPenaltyField;

    public UpdatePay(PayAlter parent, Pay pay, PayDao payDao) {
        super();
        this.pay = pay;
        this.payDao = payDao;

        setTitle("修改员工工资信息");
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 基本工资
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel basicPayLabel = new JLabel("基本工资:");
        basicPayLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(basicPayLabel, gbc);

        basicPayField = new JTextField(20);
        basicPayField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(basicPayField, gbc);

        // 职位工资
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel positionPayLabel = new JLabel("职位工资:");
        positionPayLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(positionPayLabel, gbc);

        positionPayField = new JTextField(20);
        positionPayField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(positionPayField, gbc);

        // 住房津贴
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel houseAllowanceLabel = new JLabel("住房津贴:");
        houseAllowanceLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(houseAllowanceLabel, gbc);

        houseAllowanceField = new JTextField(20);
        houseAllowanceField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(houseAllowanceField, gbc);

        // 其他津贴
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel allowanceLabel = new JLabel("其他津贴:");
        allowanceLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(allowanceLabel, gbc);

        allowanceField = new JTextField(20);
        allowanceField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(allowanceField, gbc);

        // 工会费
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel unionFeeLabel = new JLabel("工会费:");
        unionFeeLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(unionFeeLabel, gbc);

        unionFeeField = new JTextField(20);
        unionFeeField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(unionFeeField, gbc);

        // 水电费
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel weFeeLabel = new JLabel("水电费:");
        weFeeLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(weFeeLabel, gbc);

        weFeeField = new JTextField(20);
        weFeeField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(weFeeField, gbc);

        // 公积金
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel houseFundLabel = new JLabel("公积金:");
        houseFundLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(houseFundLabel, gbc);

        houseFundField = new JTextField(20);
        houseFundField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(houseFundField, gbc);

        // 养老金
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel pensionLabel = new JLabel("养老金:");
        pensionLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(pensionLabel, gbc);

        pensionField = new JTextField(20);
        pensionField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(pensionField, gbc);

        // 奖金/罚款
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel bonusPenaltyLabel = new JLabel("奖金/罚款:");
        bonusPenaltyLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(bonusPenaltyLabel, gbc);

        bonusPenaltyField = new JTextField(20);
        bonusPenaltyField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(bonusPenaltyField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 修改按钮
        JButton updateButton = new JButton("修改");
        updateButton.setFont(new Font("幼圆", Font.BOLD, 18));
        updateButton.setPreferredSize(new Dimension(100, 35));
        updateButton.addActionListener(e -> {
            updatePay();
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

    // 根据传入的Pay对象，填充表单字段
    private void populateFields() {
        basicPayField.setText(String.valueOf(pay.getBasicPay()));
        positionPayField.setText(String.valueOf(pay.getPositionPay()));
        houseAllowanceField.setText(String.valueOf(pay.getHouseAllowance()));
        allowanceField.setText(String.valueOf(pay.getAllowance()));
        unionFeeField.setText(String.valueOf(pay.getUnionFee()));
        weFeeField.setText(String.valueOf(pay.getWeFee()));
        houseFundField.setText(String.valueOf(pay.getHouseFund()));
        pensionField.setText(String.valueOf(pay.getPension()));
        bonusPenaltyField.setText(String.valueOf(pay.getBonusPenalty()));
    }

    // 更新员工工资信息
    private void updatePay() {
        pay.setBasicPay(Double.parseDouble(basicPayField.getText()));
        pay.setPositionPay(Double.parseDouble(positionPayField.getText()));
        pay.setHouseAllowance(Double.parseDouble(houseAllowanceField.getText()));
        pay.setAllowance(Double.parseDouble(allowanceField.getText()));
        pay.setUnionFee(Double.parseDouble(unionFeeField.getText()));
        pay.setWeFee(Double.parseDouble(weFeeField.getText()));
        pay.setHouseFund(Double.parseDouble(houseFundField.getText()));
        pay.setPension(Double.parseDouble(pensionField.getText()));
        pay.setBonusPenalty(Double.parseDouble(bonusPenaltyField.getText()));

        try {
            int rowsAffected = payDao.updatePay(pay);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "员工工资信息修改成功。");
                if (getParent() instanceof PayAlter) {
                    ((PayAlter) getParent()).refreshPayTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "修改员工工资信息失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "修改员工工资信息失败：" + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "请输入有效的工资数字。");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

