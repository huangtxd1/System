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

        JMenu mnNewMenu = new JMenu("�û�");
        mnNewMenu.setBackground(Color.GRAY);
        mnNewMenu.setForeground(Color.BLUE);
        JMenuItem ji = new JMenuItem("�����û�");
        ji.addActionListener(new ManageUsers());
        mnNewMenu.add(ji);
        menuBar.add(mnNewMenu);

        JMenu mnNewMenu_1 = new JMenu("Ա��");
        JMenuItem ji_1 = new JMenuItem("ע����Ա��");
        ji_1.addActionListener(new Recruit());
        mnNewMenu_1.add(ji_1);
        JMenuItem ji_2 = new JMenuItem("����Ա��");
        ji_2.addActionListener(new Expel());
        mnNewMenu_1.add(ji_2);
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_4 = new JMenu("�˳�");
        mnNewMenu_4.setForeground(Color.BLUE);
        JMenuItem menuItem = new JMenuItem("�˳�");
        mnNewMenu_4.add(menuItem);
        menuItem.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(Main.centerF, "ȷ��Ҫ�˳���");

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
