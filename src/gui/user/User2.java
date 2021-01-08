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

        JLabel title = new JLabel("��ӭע�ᣬף���ҵ����ǵķ��ӣ�");
        title.setFont(new Font("����",Font.BOLD,30));
        title.setBounds(200,60,500,50);
        userP2.add(title);

        JLabel l1 = new JLabel("*�û�����");
        l1.setForeground(Color.RED);
        l1.setBounds(240,125,60,25);
        userP2.add(l1);

        tf1.setText("���������û���");
        tf1.setBounds(300,125,250,25);
        userP2.add(tf1);

        JLabel l2 = new JLabel("*���룺");
        l2.setForeground(Color.RED);
        l2.setBounds(250,225,50,25);
        userP2.add(l2);

        tf2.setText("������������");
        tf2.setBounds(300,225,250,25);
        userP2.add(tf2);

        JButton b1 = new JButton("ע��");
        b1.setBounds(375,300,75,30);
        b1.setToolTipText("�û���������ֻ��Ϊ���ֻ���ĸ");
        b1.addActionListener(new User2_1());
        userP2.add(b1);

        JButton b2 = new JButton("������һ��");
        b2.setBounds(341,450,150,30);
        b2.addActionListener(e1 -> {
            Main.centerF.setContentPane(MUser.userMP);
            Look.look(2);
            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        });
        userP2.add(b2);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/�û�����2.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        userP2.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
