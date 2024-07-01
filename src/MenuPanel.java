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

        JMenu inputMenu = new JMenu("录入");
        menuBar.add(inputMenu);
        inputMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new InputPanel());
            }
        });

        JMenu alterMenu = new JMenu("修改");
        menuBar.add(alterMenu);

        JMenu deleteMenu = new JMenu("删除");
        menuBar.add(deleteMenu);

        JMenu searchMenu = new JMenu("查询");
        menuBar.add(searchMenu);

        JMenu countMenu = new JMenu("统计");
        menuBar.add(countMenu);

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
