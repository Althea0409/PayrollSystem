package com.lsu.view;

import com.lsu.model.Users;
import com.lsu.utils.DbUtil;
import com.lsu.dao.UserDao;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private final JTextField userTextField;
    private final JPasswordField passTextField;
    private final JComboBox<String> roleComboBox;
    private final DbUtil dbUtil = new DbUtil();
    private final UserDao userDao = new UserDao();

    // 创建主窗口
    public LoginFrame() {
        setTitle("工资管理系统");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // 间距

        // 设置字体
        Font titleFont = new Font("微软雅黑", Font.BOLD, 24);
        Font labelFont = new Font("幼圆", Font.BOLD, 20);
        Font textFieldFont = new Font("幼圆", Font.BOLD, 18);

        // 大标题
        JLabel titleLabel = new JLabel("工资管理系统", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // 添加间隔
        gbc.gridy = 1;
        add(Box.createVerticalStrut(10), gbc); // 增加高度为10的空白间隔

        // 用户名
        JLabel userLabel = new JLabel("用户名:", JLabel.RIGHT);
        userLabel.setFont(labelFont);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(userLabel, gbc);

        userTextField = new JTextField(10); // 缩小宽度
        userTextField.setFont(textFieldFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(userTextField, gbc);

        // 密码
        JLabel passLabel = new JLabel("密码:", JLabel.RIGHT);
        passLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passLabel, gbc);

        passTextField = new JPasswordField(10); // 缩小宽度
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passTextField, gbc);

        // 用户类型单选框
        JLabel typeLabel = new JLabel("权限:", JLabel.RIGHT);
        typeLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(typeLabel, gbc);

        roleComboBox = new JComboBox<>(new String[]{"员工", "管理员"});
        roleComboBox.setFont(textFieldFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(roleComboBox, gbc);

        // 添加间隔
        gbc.gridy = 5;
        add(Box.createVerticalStrut(10), gbc); // 增加高度为10的空白间隔

        // 按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // 间距20
        JButton loginButton = new JButton("登录");
        loginButton.setFont(labelFont);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });

        JButton registerButton = new JButton("注册");
        registerButton.setFont(labelFont);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regUser(e);
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // 添加水平间距
        buttonPanel.add(registerButton);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(buttonPanel, gbc);

        // 显示窗口
        setVisible(true);
        setResizable(true);
    }

    // 注册按钮点击事件
    protected void regUser(ActionEvent e) {
        this.setVisible(false);
        new RegFrame();
    }

    // 登录按钮点击事件
    protected void checkLogin() {
        String userName = userTextField.getText();
        String password = new String(passTextField.getPassword());
        int index = roleComboBox.getSelectedIndex();

        if (userName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空");
            return;
        }

        Users user = new Users();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(index == 0 ? 1 : 2);

        Connection con = null;
        try {
            con = dbUtil.getConnection();
            Users login = userDao.login(con, user);
            if (login == null) {
                JOptionPane.showMessageDialog(null, "登录失败");
            } else {
                // 权限 1-员工 2-管理员
                this.dispose();
                if (index == 0) {
                    // 员工
                    new UserFrame();
                } else {
                    // 管理员
                    new AdminFrame();
                }
            }
        } catch (Exception e21) {
            e21.printStackTrace();
            JOptionPane.showMessageDialog(null, "登录异常");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e31) {
                e31.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 设置界面风格为系统默认风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginFrame();
    }
}
