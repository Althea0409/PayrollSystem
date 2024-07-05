package com.lsu.view;

import com.lsu.dao.PayDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayInput extends JInternalFrame {

    private final JTextField staffIdField;
    private final JTextField basicPayField;
    private final JTextField positionPayField;
    private final JTextField houseAllowanceField;
    private final JTextField allowanceField;
    private final JTextField unionFeeField;
    private final JTextField weFeeField;
    private final JTextField houseFundField;
    private final JTextField pensionField;
    private final JTextField bonusPenaltyField;

    private final PayDao payDao;

    public PayInput() {
        super("工资信息输入", true, true, true, true);

        try {
            payDao = new PayDao(); // 实例化 PayDao 对象
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

        // 基本工资
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel basicPayLabel = new JLabel("基本工资:");
        basicPayLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(basicPayLabel, gbc);

        basicPayField = new JTextField(20);
        basicPayField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(basicPayField, gbc);

        // 岗位工资
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel positionPayLabel = new JLabel("岗位工资:");
        positionPayLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(positionPayLabel, gbc);

        positionPayField = new JTextField(20);
        positionPayField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(positionPayField, gbc);

        // 住房补贴
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel houseAllowanceLabel = new JLabel("住房补贴:");
        houseAllowanceLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(houseAllowanceLabel, gbc);

        houseAllowanceField = new JTextField(20);
        houseAllowanceField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(houseAllowanceField, gbc);

        // 津贴
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel allowanceLabel = new JLabel("津贴:");
        allowanceLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(allowanceLabel, gbc);

        allowanceField = new JTextField(20);
        allowanceField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(allowanceField, gbc);

        // 工会会费
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel unionFeeLabel = new JLabel("工会会费:");
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

        // 住房公积金
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel houseFundLabel = new JLabel("住房公积金:");
        houseFundLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(houseFundLabel, gbc);

        houseFundField = new JTextField(20);
        houseFundField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(houseFundField, gbc);

        // 养老保险
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel pensionLabel = new JLabel("养老保险:");
        pensionLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(pensionLabel, gbc);

        pensionField = new JTextField(20);
        pensionField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(pensionField, gbc);

        // 奖惩
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel bonusPenaltyLabel = new JLabel("奖惩:");
        bonusPenaltyLabel.setFont(new Font("幼圆", Font.BOLD, 18));
        mainPanel.add(bonusPenaltyLabel, gbc);

        bonusPenaltyField = new JTextField(20);
        bonusPenaltyField.setFont(new Font("幼圆", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(bonusPenaltyField, gbc);

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
                savePayInfo();
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

    // 保存员工工资信息到数据库
    private void savePayInfo() {
        try {
            int staffId = Integer.parseInt(staffIdField.getText());
            double basicPay = Double.parseDouble(basicPayField.getText());
            double positionPay = Double.parseDouble(positionPayField.getText());
            double houseAllowance = Double.parseDouble(houseAllowanceField.getText());
            double allowance = Double.parseDouble(allowanceField.getText());
            double unionFee = Double.parseDouble(unionFeeField.getText());
            double weFee = Double.parseDouble(weFeeField.getText());
            double houseFund = Double.parseDouble(houseFundField.getText());
            double pension = Double.parseDouble(pensionField.getText());
            double bonusPenalty = Double.parseDouble(bonusPenaltyField.getText());

            int isSaved = payDao.savePay(staffId, basicPay, positionPay, houseAllowance, allowance, unionFee, weFee, houseFund, pension, bonusPenalty);
            if (isSaved == 1) {
                JOptionPane.showMessageDialog(this, "员工工资信息保存成功！");
            } else {
                JOptionPane.showMessageDialog(this, "员工工资信息保存失败！");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的数字！", "输入错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存员工工资信息时发生错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 重置文本字段的内容
    private void resetFields() {
        staffIdField.setText("");
        basicPayField.setText("");
        positionPayField.setText("");
        houseAllowanceField.setText("");
        allowanceField.setText("");
        unionFeeField.setText("");
        weFeeField.setText("");
        houseFundField.setText("");
        pensionField.setText("");
        bonusPenaltyField.setText("");
    }
}
