package com.lsu.view;

import javax.swing.*;

public class AdminFrame {
    public AdminFrame() {
        JFrame frame = new JFrame("工资管理系统");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        MenuPanel menuPanel = new MenuPanel();
        frame.setContentPane(menuPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置初始显示的页面
        menuPanel.setInitialContent();
    }

    public static void main(String[] args) {
        // 设置界面风格为系统默认风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AdminFrame();
    }
}
