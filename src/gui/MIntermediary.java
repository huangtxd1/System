package gui;

import gui.intermediary.Intermediary;
import gui.user.User1;
import gui.user.User3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MIntermediary implements ActionListener {

    public static JPanel intermediaryMP = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {

        intermediaryMP.setLayout(null);
        Main.centerF.setContentPane(intermediaryMP);
        Look.look(1);

        JButton b1 = new JButton("我是打工人，我要直接登录！");
        b1.setBounds(250,160,300,30);
        b1.addActionListener(new Intermediary());
        intermediaryMP.add(b1);

        JButton b2 = new JButton("搞错了！我根本就不是中介！");
        b2.setBounds(250,310,300,30);
        b2.addActionListener(new User3());
        intermediaryMP.add(b2);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/中介背景0.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        intermediaryMP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
