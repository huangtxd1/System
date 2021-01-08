package gui.user;

import gui.Look;
import gui.MUser;
import gui.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User1 implements ActionListener {

    public static JPanel userP1 = new JPanel();
    static JTextField tf1 = new JTextField("");
    static JTextField tf2 = new JTextField("");

    @Override
    public void actionPerformed(ActionEvent e) {

        userP1.setLayout(null);
        Main.centerF.setContentPane(userP1);
        Look.look(1);

        JLabel l1 = new JLabel("用户名：");
        l1.setBounds(250,125,50,25);
        userP1.add(l1);

        tf1.setText("请输入用户名");
        tf1.setBounds(300,125,250,25);
        userP1.add(tf1);

        JLabel l2 = new JLabel("密码：");
        l2.setBounds(250,225,50,25);
        userP1.add(l2);

        tf2.setText("请输入密码");
        tf2.setBounds(300,225,250,25);
        userP1.add(tf2);

        JButton b1 = new JButton("登录");
        b1.setBounds(375,300,75,30);
        b1.addActionListener(new User1_1());
        userP1.add(b1);

        JButton b2 = new JButton("返回上一步");
        b2.setBounds(341,450,150,30);
        b2.addActionListener(e1 -> {
            Main.centerF.setContentPane(MUser.userMP);
            Look.look(1);
            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        });
        userP1.add(b2);

        JLabel l3 = new JLabel();
        ImageIcon i = new ImageIcon("d:/j2se/用户头像.jpg");
        l3.setIcon(i);
        l3.setBounds(337,1,i.getIconWidth(),i.getIconHeight());
        userP1.add(l3);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/用户背景1.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        userP1.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
