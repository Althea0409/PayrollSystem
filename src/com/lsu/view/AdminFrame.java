package com.lsu.view;

import javax.swing.*;

public class AdminFrame {
    public static void main(String[] args) {

        JFrame frame = new JFrame("员工工资管理系统");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MenuPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置界面风格为系统默认风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}