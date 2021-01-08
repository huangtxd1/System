package gui.user;

import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UMB {

    public JMenuBar menuBar = new JMenuBar();

    public UMB(){
        example();
    }

    private void example(){

        menuBar.setBackground(Color.RED);
        Main.centerF.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("房源");
        mnNewMenu.setBackground(Color.GRAY);
        mnNewMenu.setForeground(Color.BLUE);
        JMenuItem menuItem = new JMenuItem("房源");
        mnNewMenu.add(menuItem);
        menuItem.addActionListener(new HousingResources());
        menuBar.add(mnNewMenu);

        JMenu mnNewMenu_1 = new JMenu("我收藏的");
        mnNewMenu_1.setForeground(Color.BLUE);
        JMenuItem menuItem1 = new JMenuItem("我收藏的");
        mnNewMenu_1.add(menuItem1);
        menuItem1.addActionListener(new MyCollection());
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_2 = new JMenu("我预约的");
        mnNewMenu_2.setForeground(Color.BLUE);
        JMenuItem menuItem2 = new JMenuItem("我预约的");
        mnNewMenu_2.add(menuItem2);
        menuItem2.addActionListener(new MyAppointment());
        menuBar.add(mnNewMenu_2);

        JMenu mnNewMenu_4 = new JMenu("退出");
        mnNewMenu_4.setForeground(Color.BLUE);
        JMenuItem menuItem3 = new JMenuItem("退出");
        mnNewMenu_4.add(menuItem3);
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int option = JOptionPane.showConfirmDialog(Main.centerF,"确定要退出？");

                if(JOptionPane.OK_OPTION == option){

                    menuBar.setVisible(false);
                    Main.centerF.setContentPane(User1.userP1);
                    Look.look(3);
                    Main.centerF.setVisible(false);
                    Main.centerF.setVisible(true);
                }
            }
        });
        menuBar.add(mnNewMenu_4);

        menuBar.setVisible(false);
    }
}
