package gui.user;

import gui.Look;
import gui.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HR_1 implements ActionListener {

    public static JTextField textField = new JTextField("0");
    public static JTextField textField_1 = new JTextField("50");
    public static JComboBox comboBox = new JComboBox();
    public static JComboBox comboBox_1 = new JComboBox();
    public static JTextField textField_2 = new JTextField("0");
    public static JTextField textField_3 = new JTextField("10000");

    public static boolean isNumeric (String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        if (str.length() != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel hRP_1 = new JPanel();
        hRP_1.setLayout(null);
        Main.centerF.setContentPane(hRP_1);
        Look.look(1);

        JLabel lblNewLabel = new JLabel("\u623F\u5C4B\u9762\u79EF\uFF1A");
        lblNewLabel.setBounds(0, 80, 60, 30);
        hRP_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u5230");
        lblNewLabel_1.setBounds(140, 80, 20, 30);
        hRP_1.add(lblNewLabel_1);

        textField.setBounds(75, 80, 60, 30);
        hRP_1.add(textField);
        textField.setColumns(10);

        textField_1.setBounds(165, 80, 60, 30);
        hRP_1.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("\u5E73\u65B9\u7C73");
        lblNewLabel_2.setBounds(230, 80, 60, 30);
        hRP_1.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("\u623F\u5C4B\u5730\u70B9\uFF1A\r\n");
        lblNewLabel_3.setBounds(400, 80, 60, 30);
        hRP_1.add(lblNewLabel_3);

        comboBox.setModel(new DefaultComboBoxModel(new String[] {"永定区", "武陵源区", "慈利县", "桑植县"}));
        comboBox.setBounds(475, 80, 200, 30);
        hRP_1.add(comboBox);

        JLabel lblNewLabel_4 = new JLabel("\u623F\u5C4B\u6837\u5F0F\uFF1A");
        lblNewLabel_4.setBounds(400, 240, 60, 30);
        hRP_1.add(lblNewLabel_4);

        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"一室", "一室一厅", "两室一厅", "两室两厅", "三室一厅", "三室两厅", "四室一厅", "四室两厅", "五室一厅", "五室两厅","五室三厅"}));
        comboBox_1.setBounds(475, 240, 200, 30);
        hRP_1.add(comboBox_1);

        JLabel lblNewLabel_5 = new JLabel("\u623F\u5C4B\u4EF7\u683C\uFF1A");
        lblNewLabel_5.setBounds(0, 240, 60, 30);
        hRP_1.add(lblNewLabel_5);

        textField_2.setBounds(75, 240, 60, 30);
        hRP_1.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("\u5230");
        lblNewLabel_6.setBounds(140, 240, 20, 30);
        hRP_1.add(lblNewLabel_6);

        textField_3.setBounds(165, 240, 60, 30);
        hRP_1.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("\u5143\r\n");
        lblNewLabel_7.setBounds(230, 240, 60, 30);
        hRP_1.add(lblNewLabel_7);

        JButton btnNewButton_4 = new JButton("确认");
        btnNewButton_4.setBounds(295, 380, 150, 40);
        btnNewButton_4.addActionListener(new HR_1_1());
        hRP_1.add(btnNewButton_4);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/房源_1.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        hRP_1.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
