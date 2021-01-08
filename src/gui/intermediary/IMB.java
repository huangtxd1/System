package gui.intermediary;

import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMB {

    public JMenuBar menuBar = new JMenuBar();

    public IMB(){
        example();
    }

    private void example() {

        menuBar.setBackground(Color.RED);
        Main.centerF.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("工具");
        mnNewMenu.setBackground(Color.GRAY);
        mnNewMenu.setForeground(Color.BLUE);
        menuBar.add(mnNewMenu);

        JMenuItem ji = new JMenuItem("房屋处理");
        ji.addActionListener(new AddHouse());
        mnNewMenu.add(ji);
        JMenuItem ji_1 = new JMenuItem("受理预约");
        ji_1.addActionListener(new AcceptAppointment());
        mnNewMenu.add(ji_1);
        JMenuItem ji_2 = new JMenuItem("回复评论");
        ji_2.addActionListener(new AnswerUser());
        mnNewMenu.add(ji_2);

        JMenu mnNewMenu_4 = new JMenu("退出");
        mnNewMenu_4.setForeground(Color.BLUE);
        JMenuItem menuItem = new JMenuItem("退出");
        mnNewMenu_4.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int option = JOptionPane.showConfirmDialog(Main.centerF, "确定要退出？");

                if (JOptionPane.OK_OPTION == option) {

                    menuBar.setVisible(false);
                    Main.centerF.setContentPane(Intermediary.intermediaryP);
                    Look.look(4);
                    Main.centerF.setVisible(false);
                    Main.centerF.setVisible(true);
                }
            }
        });
        menuBar.add(mnNewMenu_4);

        menuBar.setVisible(false);
    }
}
