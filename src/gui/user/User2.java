package gui.user;

import gui.Look;
import gui.MUser;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User2 implements ActionListener {

    public static Panel userP2 = new Panel();
    static JTextField tf1 = new JTextField("");
    static JTextField tf2 = new JTextField("");

    @Override
    public void actionPerformed(ActionEvent e) {

        userP2.setLayout(null);
        Main.centerF.setContentPane(userP2);
        Look.look(1);

        JLabel title = new JLabel("欢迎注册，祝您找到心仪的房子！");
        title.setFont(new Font("宋体",Font.BOLD,30));
        title.setBounds(200,60,500,50);
        userP2.add(title);

        JLabel l1 = new JLabel("*用户名：");
        l1.setForeground(Color.RED);
        l1.setBounds(240,125,60,25);
        userP2.add(l1);

        tf1.setText("请输入新用户名");
        tf1.setBounds(300,125,250,25);
        userP2.add(tf1);

        JLabel l2 = new JLabel("*密码：");
        l2.setForeground(Color.RED);
        l2.setBounds(250,225,50,25);
        userP2.add(l2);

        tf2.setText("请输入新密码");
        tf2.setBounds(300,225,250,25);
        userP2.add(tf2);

        JButton b1 = new JButton("注册");
        b1.setBounds(375,300,75,30);
        b1.setToolTipText("用户名和密码只能为数字或字母");
        b1.addActionListener(new User2_1());
        userP2.add(b1);

        JButton b2 = new JButton("返回上一步");
        b2.setBounds(341,450,150,30);
        b2.addActionListener(e1 -> {
            Main.centerF.setContentPane(MUser.userMP);
            Look.look(2);
            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        });
        userP2.add(b2);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/用户背景2.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        userP2.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
