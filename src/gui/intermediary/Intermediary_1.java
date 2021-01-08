package gui.intermediary;

import gui.Look;
import gui.Main;
import gui.user.UMB;
import gui.user.User1_1;
import jdbc.IntermediaryDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Intermediary_1 implements ActionListener {

    public static JPanel intermediaryP_1 = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!Intermediary.tf1.getText().equals("")&&!Intermediary.tf2.getText().equals("")){

            IntermediaryDAO intermediaryDAO = new IntermediaryDAO();

            if(User1_1.isNumericOrLetter(Intermediary.tf1.getText())&& User1_1.isNumericOrLetter(Intermediary.tf2.getText())){
                if(!intermediaryDAO.duplicateChecking(Intermediary.tf1.getText(),Intermediary.tf2.getText())){

                    JOptionPane.showMessageDialog(Main.centerF,"��¼�ɹ�");

                    intermediaryP_1.setLayout(null);
                    Main.centerF.setContentPane(intermediaryP_1);
                    Look.look(1);

                    new IMB().menuBar.setVisible(true);

                    JLabel title = new JLabel("ѹե���û�����");
                    title.setForeground(Color.WHITE);
                    title.setFont(new Font("����",Font.BOLD,40));
                    title.setBounds(250,120,400,60);
                    intermediaryP_1.add(title);

                    JLabel title1 = new JLabel("�ļ�ֵ��");
                    title1.setForeground(Color.WHITE);
                    title1.setFont(new Font("����",Font.BOLD,40));
                    title1.setBounds(250,220,400,60);
                    intermediaryP_1.add(title1);

                    JLabel backgroundPicture = new JLabel();
                    ImageIcon picture = new ImageIcon("d:/j2se/�н鱳��1_1.jpg");
                    backgroundPicture.setIcon(picture);
                    backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
                    intermediaryP_1.add(backgroundPicture);

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
