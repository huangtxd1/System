package gui.intermediary;

import gui.Look;
import gui.MIntermediary;
import gui.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Intermediary implements ActionListener {

    public static JPanel intermediaryP = new JPanel();
    static JTextField tf1 = new JTextField("");
    static JTextField tf2 = new JTextField("");

    @Override
    public void actionPerformed(ActionEvent e) {

        intermediaryP.setLayout(null);
        Main.centerF.setContentPane(intermediaryP);
        Look.look(1);

        JLabel l1 = new JLabel("用户名：");
        l1.setBounds(250,125,50,25);
        intermediaryP.add(l1);

        tf1.setText("请输入用户名");
        tf1.setBounds(300,125,250,25);
        intermediaryP.add(tf1);

        JLabel l2 = new JLabel("密码：");
        l2.setBounds(250,225,50,25);
        intermediaryP.add(l2);

        tf2.setText("请输入密码");
        tf2.setBounds(300,225,250,25);
        intermediaryP.add(tf2);

        JButton b1 = new JButton("登录");
        b1.setBounds(375,300,75,30);
        b1.addActionListener(new Intermediary_1());
        intermediaryP.add(b1);

        JButton b2 = new JButton("返回上一步");
        b2.setBounds(341,450,150,30);
        b2.addActionListener(e1 -> {
            Main.centerF.setContentPane(MIntermediary.intermediaryMP);
            Look.look(1);
            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        });
        intermediaryP.add(b2);

        JLabel l3 = new JLabel();
        ImageIcon i = new ImageIcon("d:/j2se/中介头像.jpg");
        l3.setIcon(i);
        l3.setBounds(337,1,i.getIconWidth(),i.getIconHeight());
        intermediaryP.add(l3);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/中介背景1.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        intermediaryP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
