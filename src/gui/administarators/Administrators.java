package gui.administarators;

import gui.Look;
import gui.MIntermediary;
import gui.Main;
import gui.intermediary.Intermediary_1;
import gui.user.User3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrators implements ActionListener {

    public static JPanel administratorsP = new JPanel();
    static JTextField tf1 = new JTextField("");
    static JTextField tf2 = new JTextField("");

    @Override
    public void actionPerformed(ActionEvent e) {

        int option = JOptionPane.showConfirmDialog(Main.centerF, "�Ծ��㣡��ȷ�����ǹ���Ա��");
        if (JOptionPane.OK_OPTION == option){

            administratorsP.setLayout(null);
            Main.centerF.setContentPane(administratorsP);
            Look.look(1);

            JLabel l1 = new JLabel("�û�����");
            l1.setForeground(Color.WHITE);
            l1.setBounds(250,125,50,25);
            administratorsP.add(l1);

            tf1.setText("������Ψһ�û���");
            tf1.setBounds(300,125,250,25);
            administratorsP.add(tf1);

            JLabel l2 = new JLabel("���룺");
            l2.setForeground(Color.WHITE);
            l2.setBounds(250,225,50,25);
            administratorsP.add(l2);

            tf2.setText("������Ψһ����");
            tf2.setBounds(300,225,250,25);
            administratorsP.add(tf2);

            JButton b1 = new JButton("��¼");
            b1.setBounds(375,300,75,30);
            b1.addActionListener(new Administrators_1());
            administratorsP.add(b1);

            JButton b2 = new JButton("������һ��");
            b2.setBounds(341,450,150,30);
            b2.addActionListener(new User3());
            administratorsP.add(b2);

            JLabel l3 = new JLabel();
            ImageIcon i = new ImageIcon("d:/j2se/����Աͷ��.jpg");
            l3.setIcon(i);
            l3.setBounds(337,1,i.getIconWidth(),i.getIconHeight());
            administratorsP.add(l3);

            JLabel backgroundPicture = new JLabel();
            ImageIcon picture = new ImageIcon("d:/j2se/����Ա����0.jpg");
            backgroundPicture.setIcon(picture);
            backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
            administratorsP.add(backgroundPicture);

            Main.centerF.setVisible(false);
            Main.centerF.setVisible(true);
        }
    }
}
