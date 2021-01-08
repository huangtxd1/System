package gui.administarators;

import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrators_1 implements ActionListener {

    public static JPanel administratorsP_1 = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Administrators.tf1.getText().equals("huang") && Administrators.tf2.getText().equals("ruiqi")){

            JOptionPane.showMessageDialog(Main.centerF,"管理员好！");

            administratorsP_1.setLayout(null);
            Main.centerF.setContentPane(administratorsP_1);
            Look.look(1);

            new AMB().menuBar.setVisible(true);

            JLabel backgroundPicture = new JLabel();
            ImageIcon picture = new ImageIcon("d:/j2se/管理员背景1.jpg");
            backgroundPicture.setIcon(picture);
            backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
            administratorsP_1.add(backgroundPicture);

            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(Main.centerF,"你不是管理员！");
        }
    }
}
