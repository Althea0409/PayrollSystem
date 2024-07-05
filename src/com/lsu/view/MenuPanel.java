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

        // 员工信息录入
        JMenu inputMenu = new JMenu("输入与保存");
        menuBar.add(inputMenu);

        JMenuItem staffInput = new JMenuItem("基本信息");
        inputMenu.add(staffInput);
        staffInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new StaffInput());
            }
        });

        JMenuItem payInput = new JMenuItem("工资信息");
        inputMenu.add(payInput);
        payInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayInput());
            }
        });

        JMenuItem dptInput = new JMenuItem("部门信息");
        inputMenu.add(dptInput);
        dptInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new DptInput());
            }
        });

        // 修改与删除
        JMenu alterMenu = new JMenu("修改与删除");
        menuBar.add(alterMenu);

        JMenuItem staffAlter = new JMenuItem("基本信息");
        alterMenu.add(staffAlter);
        staffAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new StaffAlter());
            }
        });

        JMenuItem payAlter = new JMenuItem("工资信息");
        alterMenu.add(payAlter);
        payAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PayAlter());
            }
        });

        JMenuItem dptAlter = new JMenuItem("部门信息");
        alterMenu.add(dptAlter);
        dptAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new DptAlter());
            }
        });

        // 查询与统计
        JMenu searchMenu = new JMenu("查询与统计");
        menuBar.add(searchMenu);

        JMenuItem staffSearch = new JMenuItem("基本信息");
        searchMenu.add(staffSearch);
        staffSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new StaffSearch());
            }
        });

        JMenuItem paySearch = new JMenuItem("工资信息");
        searchMenu.add(paySearch);
        paySearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new PaySearch());
            }
        });

        JMenuItem dptSearch = new JMenuItem("部门信息");
        searchMenu.add(dptSearch);
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
