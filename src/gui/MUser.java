package gui;

import gui.user.User1;
import gui.user.User2;
import gui.user.User3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MUser implements ActionListener {

    public static JPanel userMP = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {

        userMP.setLayout(null);
        Main.centerF.setContentPane(userMP);
        Look.look(1);

        JButton b1 = new JButton("�������˺ţ���Ҫֱ�ӵ�¼��");
        b1.setBounds(250,70,300,30);
        b1.addActionListener(new User1());
        userMP.add(b1);

        JButton b2 = new JButton("��û���˺ţ���Ҫ����ע�ᣡ");
        b2.setBounds(250,220,300,30);
        b2.addActionListener(new User2());
        userMP.add(b2);

        JButton b3 = new JButton("����ˣ��Ҹ����Ͳ����û���");
        b3.setBounds(250,370,300,30);
        b3.addActionListener(new User3());
        userMP.add(b3);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/�û�����0.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        userMP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
