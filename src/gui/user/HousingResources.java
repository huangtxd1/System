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

        JButton btnNewButton = new JButton("全部分类");
        btnNewButton.setToolTipText("能够最快速地找到您想要的房屋");
        btnNewButton.addActionListener(new HR_1());
        toolBar.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("搜索房屋");
        btnNewButton_1.setToolTipText("通过搜索房屋名快速找到房屋");
        btnNewButton_1.addActionListener(new HR_2());
        toolBar.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("热门房屋");
        btnNewButton_2.setToolTipText("收藏中热门");
        toolBar.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("推荐房屋");
        btnNewButton_3.setToolTipText("中介推荐的良心房屋");
        toolBar.add(btnNewButton_3);

        hRP.add(toolBar);

        JLabel title = new JLabel("请使用以上工具查找！");
        title.setFont(new Font("宋体",Font.BOLD,40));
        title.setForeground(Color.blue);
        title.setBounds(150,200,500,50);
        hRP.add(title);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/房源.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        hRP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
