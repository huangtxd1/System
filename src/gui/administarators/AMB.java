package gui.administarators;

import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.*;

public class AMB {

    public JMenuBar menuBar = new JMenuBar();

    public AMB(){
        example();
    }

    private void example() {

        menuBar.setBackground(Color.RED);
        Main.centerF.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("用户");
        mnNewMenu.setBackground(Color.GRAY);
        mnNewMenu.setForeground(Color.BLUE);
        JMenuItem ji = new JMenuItem("管理用户");
        ji.addActionListener(new ManageUsers());
        mnNewMenu.add(ji);
        menuBar.add(mnNewMenu);

        JMenu mnNewMenu_1 = new JMenu("员工");
        JMenuItem ji_1 = new JMenuItem("注册新员工");
        ji_1.addActionListener(new Recruit());
        mnNewMenu_1.add(ji_1);
        JMenuItem ji_2 = new JMenuItem("开除员工");
        ji_2.addActionListener(new Expel());
        mnNewMenu_1.add(ji_2);
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_4 = new JMenu("退出");
        mnNewMenu_4.setForeground(Color.BLUE);
        JMenuItem menuItem = new JMenuItem("退出");
        mnNewMenu_4.add(menuItem);
        menuItem.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(Main.centerF, "确定要退出？");

            if (JOptionPane.OK_OPTION == option) {

                menuBar.setVisible(false);
                Main.centerF.setContentPane(Administrators.administratorsP);
                Look.look(4);
                Main.centerF.setVisible(false);
                Main.centerF.setVisible(true);
            }
        });
        menuBar.add(mnNewMenu_4);

        menuBar.setVisible(false);
    }
}
