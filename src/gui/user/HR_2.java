package gui.user;

import charactor.House;
import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HR_2 implements ActionListener {

    public static JTextField textField = new JTextField();

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel hRP_2 = new JPanel();
        hRP_2.setLayout(null);
        Main.centerF.setContentPane(hRP_2);
        Look.look(1);

        textField.setBounds(245, 210, 300, 40);
        hRP_2.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("ËÑË÷£º");
        btnNewButton.setFont(new Font("ËÎÌå", Font.BOLD, 16));
        btnNewButton.setBounds(160, 210, 80, 40);
        btnNewButton.addActionListener(new HR_2_1());
        hRP_2.add(btnNewButton);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/·¿Ô´_2.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        hRP_2.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
