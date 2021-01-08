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

        JLabel l1 = new JLabel("�û�����");
        l1.setBounds(250,125,50,25);
        intermediaryP.add(l1);

        tf1.setText("�������û���");
        tf1.setBounds(300,125,250,25);
        intermediaryP.add(tf1);

        JLabel l2 = new JLabel("���룺");
        l2.setBounds(250,225,50,25);
        intermediaryP.add(l2);

        tf2.setText("����������");
        tf2.setBounds(300,225,250,25);
        intermediaryP.add(tf2);

        JButton b1 = new JButton("��¼");
        b1.setBounds(375,300,75,30);
        b1.addActionListener(new Intermediary_1());
        intermediaryP.add(b1);

        JButton b2 = new JButton("������һ��");
        b2.setBounds(341,450,150,30);
        b2.addActionListener(e1 -> {
            Main.centerF.setContentPane(MIntermediary.intermediaryMP);
            Look.look(1);
            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        });
        intermediaryP.add(b2);

        JLabel l3 = new JLabel();
        ImageIcon i = new ImageIcon("d:/j2se/�н�ͷ��.jpg");
        l3.setIcon(i);
        l3.setBounds(337,1,i.getIconWidth(),i.getIconHeight());
        intermediaryP.add(l3);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/�н鱳��1.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        intermediaryP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
