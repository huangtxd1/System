package gui.user;

import gui.Look;
import gui.Main;
import jdbc.UserOrAdministratorsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User1_1 implements ActionListener {

    public static JPanel userP1_1 = new JPanel();

    public static boolean isNumericOrLetter (String str){

        String letter = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";

        for (int i = str.length();--i>=0;){

            if(letter.indexOf(str.charAt(i)) < 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!User1.tf1.getText().equals("")&&!User1.tf2.getText().equals("")){

            UserOrAdministratorsDAO userOrAdministratorsDAO = new UserOrAdministratorsDAO();

            if(isNumericOrLetter(User1.tf1.getText())&&isNumericOrLetter(User1.tf2.getText())){
                if(!userOrAdministratorsDAO.duplicateChecking(User1.tf1.getText(),User1.tf2.getText())){

                    JOptionPane.showMessageDialog(Main.centerF,"��¼�ɹ�");

                    userP1_1.setLayout(null);
                    Main.centerF.setContentPane(userP1_1);
                    Look.look(1);

                    new UMB().menuBar.setVisible(true);

                    JLabel title = new JLabel("һ�󲨷�������");
                    title.setForeground(Color.red);
                    title.setFont(new Font("����",Font.BOLD,40));
                    title.setBounds(250,120,400,60);
                    userP1_1.add(title);

                    JLabel title1 = new JLabel("��Ϯ����");
                    title1.setForeground(Color.red);
                    title1.setFont(new Font("����",Font.BOLD,40));
                    title1.setBounds(250,220,400,60);
                    userP1_1.add(title1);

                    JLabel backgroundPicture = new JLabel();
                    ImageIcon picture = new ImageIcon("d:/j2se/�û�����1_1.jpg");
                    backgroundPicture.setIcon(picture);
                    backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
                    userP1_1.add(backgroundPicture);

                    Main.centerF.setVisible(false);
                    Main.centerF.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(Main.centerF,"�û������������");
                }
            }else{
                JOptionPane.showMessageDialog(Main.centerF,"�벻Ҫ������Ч���ݨt���F���䣩�s");
            }
        }else{
            JOptionPane.showMessageDialog(Main.centerF,"�û��������벻��Ϊ��");
        }
    }
}
