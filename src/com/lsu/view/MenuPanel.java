package com.lsu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {
    private static final JDesktopPane contentPanel = new JDesktopPane();

    public MenuPanel() {
        JMenuBar menuBar = new JMenuBar();
        this.setLayout(new BorderLayout());
        this.add(menuBar, BorderLayout.NORTH);

        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.removeAll();
        contentPanel.repaint();

        // 员工信息操作
        JMenu staffMenu = new JMenu("员工信息");
        menuBar.add(staffMenu);

        JMenuItem staffInput = new JMenuItem("输入");
        staffMenu.add(staffInput);
        staffInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new StaffInput());
            }
        });

        JMenuItem staffAlter = new JMenuItem("修改");
        staffMenu.add(staffAlter);
        staffAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new StaffAlter());
            }
        });

        JMenuItem staffSearch = new JMenuItem("查询");
        staffMenu.add(staffSearch);
        staffSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new StaffSearch());
            }
        });

        // 工资信息操作
        JMenu payMenu = new JMenu("工资信息");
        menuBar.add(payMenu);

        JMenuItem payInput = new JMenuItem("输入");
        payMenu.add(payInput);
        payInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayInput());
            }
        });

        JMenuItem payAlter = new JMenuItem("修改");
        payMenu.add(payAlter);
        payAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayAlter());
            }
        });

        JMenuItem paySearch = new JMenuItem("查询");
        payMenu.add(paySearch);
        paySearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PaySearch());
            }
        });

        JMenuItem payCount = new JMenuItem("统计");
        payMenu.add(payCount);
        payCount.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayCount());
            }
        });

        // 部门信息操作
        JMenu dptMenu = new JMenu("部门信息");
        menuBar.add(dptMenu);

        JMenuItem dptInput = new JMenuItem("输入");
        dptMenu.add(dptInput);
        dptInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new DptInput());
            }
        });

        JMenuItem dptAlter = new JMenuItem("修改");
        dptMenu.add(dptAlter);
        dptAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new DptAlter());
            }
        });

        JMenuItem dptSearch = new JMenuItem("查询");
        dptMenu.add(dptSearch);
        dptSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new DptSearch());
            }
        });

        // 退出当前系统
        JMenu exitMenu = new JMenu("退出系统");
        menuBar.add(exitMenu);
        exitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 退出系统逻辑
                String message = "确定退出系统吗？";
                int option = JOptionPane.showConfirmDialog(null, message, "确认退出", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // 退出程序
                }
            }
        });
    }

    // 设置初始显示的页面
    public void setInitialContent() {
        setContent(new StaffInput());
    }

    public static void setContent(JInternalFrame internalFrame) {
        internalFrame.setSize(885, 540);
        // 显示内部窗口
        internalFrame.setVisible(true);
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.add(internalFrame);
    }
}
