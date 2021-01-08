package gui.user;

import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HousingResources implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel hRP = new JPanel();
        hRP.setLayout(null);
        Main.centerF.setContentPane(hRP);
        Look.look(1);

        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, 786, 63);

        JButton btnNewButton = new JButton("ȫ������");
        btnNewButton.setToolTipText("�ܹ�����ٵ��ҵ�����Ҫ�ķ���");
        btnNewButton.addActionListener(new HR_1());
        toolBar.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("��������");
        btnNewButton_1.setToolTipText("ͨ�����������������ҵ�����");
        btnNewButton_1.addActionListener(new HR_2());
        toolBar.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("���ŷ���");
        btnNewButton_2.setToolTipText("�ղ�������");
        toolBar.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("�Ƽ�����");
        btnNewButton_3.setToolTipText("�н��Ƽ������ķ���");
        toolBar.add(btnNewButton_3);

        hRP.add(toolBar);

        JLabel title = new JLabel("��ʹ�����Ϲ��߲��ң�");
        title.setFont(new Font("����",Font.BOLD,40));
        title.setForeground(Color.blue);
        title.setBounds(150,200,500,50);
        hRP.add(title);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/��Դ.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        hRP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
