package gui;

import gui.administarators.Administrators;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame centerF = new JFrame("�����н����ϵͳ");
    public static JPanel centerP = new JPanel();

    public static int search;

    public static void main(String[] args) {

        //����������
        centerF.setLocation(200,100);
        centerF.setSize(800,600);
        centerF.setLayout(null);

        //���������
        centerP.setLayout(null);
        centerF.setContentPane(centerP);
        Look.look(1);

        JLabel title = new JLabel("��ӭʹ�÷����н����ϵͳ");
        title.setFont(new Font("����",Font.BOLD,30));
        title.setBounds(200,100,400,50);
        centerP.add(title);

        JButton b1 = new JButton("�����û�");
        b1.setBounds(300,200,200,30);
        b1.addActionListener(new MUser());
        centerP.add(b1);

        JButton b2 = new JButton("�����н�");
        b2.setBounds(300,280,200,30);
        b2.addActionListener(new MIntermediary());
        centerP.add(b2);

        JButton b3 = new JButton("���ǹ���Ա");
        b3.setBounds(300,360,200,30);
        b3.addActionListener(new Administrators());
        centerP.add(b3);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/����.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        centerP.add(backgroundPicture);

        /*new UMB();
        UMB.menuBar.setVisible(false);*/

        centerF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerF.setVisible(true);
    }
}
