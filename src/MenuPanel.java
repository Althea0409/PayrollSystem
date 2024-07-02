import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {
    private static JDesktopPane contentPanel = new JDesktopPane();

    public MenuPanel() {
        JMenuBar menuBar = new JMenuBar();
        this.setLayout(new BorderLayout());
        this.add(menuBar, BorderLayout.NORTH);

        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.removeAll();
        contentPanel.repaint();

        // 信息录入功能
        JMenu inputMenu = new JMenu("录入");
        menuBar.add(inputMenu);

        JMenuItem inputMenu1 = new JMenuItem("员工信息");
        inputMenu.add(inputMenu1);
        inputMenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new InputPanel1());
            }
        });

        JMenuItem inputMenu2 = new JMenuItem("工资信息");
        inputMenu.add(inputMenu2);
        inputMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new InputPanel2());
            }
        });

        JMenuItem inputMenu3 = new JMenuItem("部门信息");
        inputMenu.add(inputMenu3);
        inputMenu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new InputPanel3());
            }
        });

        // 信息修改功能
        JMenu alterMenu = new JMenu("修改");
        menuBar.add(alterMenu);

        JMenuItem alterMenu1 = new JMenuItem("员工信息");
        alterMenu.add(alterMenu1);
        alterMenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new AlterPanel1());
            }
        });

        JMenuItem alterMenu2 = new JMenuItem("工资信息");
        alterMenu.add(alterMenu2);
        alterMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new AlterPanel2());
            }
        });

        JMenuItem alterMenu3 = new JMenuItem("部门信息");
        alterMenu.add(alterMenu3);
        alterMenu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new AlterPanel3());
            }
        });

        // 信息查询功能
        JMenu searchMenu = new JMenu("查询");
        menuBar.add(searchMenu);

        JMenuItem searchMenu1 = new JMenuItem("员工信息");
        searchMenu.add(searchMenu1);
        searchMenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new SearchPanel1());
            }
        });

        JMenuItem searchMenu2 = new JMenuItem("工资信息");
        searchMenu.add(searchMenu2);
        searchMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new SearchPanel2());
            }
        });

        JMenuItem searchMenu3 = new JMenuItem("部门信息");
        searchMenu.add(searchMenu3);
        searchMenu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new SearchPanel3());
            }
        });

        // 信息统计功能
        JMenu countMenu = new JMenu("统计");
        menuBar.add(countMenu);

        JMenuItem countMenu1 = new JMenuItem("员工信息");
        countMenu.add(countMenu1);
        countMenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new CountPanel1());
            }
        });

        JMenuItem countMenu2 = new JMenuItem("工资信息");
        countMenu.add(countMenu2);
        countMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new CountPanel2());
            }
        });

        JMenuItem countMenu3 = new JMenuItem("部门信息");
        countMenu.add(countMenu3);
        countMenu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new CountPanel3());
            }
        });

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
