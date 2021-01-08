package gui.administarators;

import charactor.Intermediary;
import gui.Look;
import gui.Main;
import gui.user.User1_1;
import jdbc.IntermediaryDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Expel implements ActionListener {

    static JTextField tf1 = new JTextField("");
    static JTextField tf2 = new JTextField("");

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel eP = new JPanel();
        eP.setLayout(null);
        Main.centerF.setContentPane(eP);
        Look.look(1);

        JLabel title = new JLabel("������⣡����㣡");
        title.setFont(new Font("����",Font.BOLD,30));
        title.setBounds(225,60,350,50);
        eP.add(title);

        JLabel l1 = new JLabel("*�û�����");
        l1.setForeground(Color.RED);
        l1.setBounds(240,140,60,25);
        eP.add(l1);

        tf1.setText("������Ҫ������Ա���û���");
        tf1.setBounds(300,140,250,25);
        eP.add(tf1);

        JLabel l2 = new JLabel("*���룺");
        l2.setForeground(Color.RED);
        l2.setBounds(250,240,50,25);
        eP.add(l2);

        tf2.setText("������Ҫ������Ա������");
        tf2.setBounds(300,240,250,25);
        eP.add(tf2);

        JButton b1 = new JButton("����");
        b1.setBounds(375,365,75,30);
        b1.setToolTipText("�û���������ֻ��Ϊ���ֻ���ĸ");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!tf1.getText().equals("") && !tf2.getText().equals("")){

                    IntermediaryDAO intermediaryDAO = new IntermediaryDAO();

                    if(User1_1.isNumericOrLetter(tf1.getText()) && User1_1.isNumericOrLetter(tf2.getText())){

                        Intermediary intermediary = new Intermediary(tf1.getText(),tf2.getText());
                        intermediaryDAO.delete(intermediary);
                        int option = JOptionPane.showConfirmDialog(Main.centerF, "ȷ��Ҫ�˳���");
                        if (JOptionPane.OK_OPTION == option)
                            JOptionPane.showMessageDialog(Main.centerF,"�����ɹ�");
                    }else{
                        JOptionPane.showMessageDialog(Main.centerF,"�û����������ʽ����ȷ");
                    }
                }else{
                    JOptionPane.showMessageDialog(Main.centerF,"�û��������벻��Ϊ��");
                }
            }
        });
        eP.add(b1);

        JLabel backgroundPicture = new JLabel();
        ImageIcon picture = new ImageIcon("d:/j2se/����Ա����3.jpg");
        backgroundPicture.setIcon(picture);
        backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
        eP.add(backgroundPicture);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
