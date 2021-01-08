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

                    JOptionPane.showMessageDialog(Main.centerF,"登录成功");

                    intermediaryP_1.setLayout(null);
                    Main.centerF.setContentPane(intermediaryP_1);
                    Look.look(1);

                    new IMB().menuBar.setVisible(true);

                    JLabel title = new JLabel("压榨出用户所有");
                    title.setForeground(Color.WHITE);
                    title.setFont(new Font("宋体",Font.BOLD,40));
                    title.setBounds(250,120,400,60);
                    intermediaryP_1.add(title);

                    JLabel title1 = new JLabel("的价值！");
                    title1.setForeground(Color.WHITE);
                    title1.setFont(new Font("宋体",Font.BOLD,40));
                    title1.setBounds(250,220,400,60);
                    intermediaryP_1.add(title1);

                    JLabel backgroundPicture = new JLabel();
                    ImageIcon picture = new ImageIcon("d:/j2se/中介背景1_1.jpg");
                    backgroundPicture.setIcon(picture);
                    backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
                    intermediaryP_1.add(backgroundPicture);

                    Main.centerF.setVisible(false);
                    Main.centerF.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(Main.centerF,"用户名或密码错误");
                }
            }else{
                JOptionPane.showMessageDialog(Main.centerF,"请不要输入无效数据╰（‵□′）╯");
            }
        }else{
            JOptionPane.showMessageDialog(Main.centerF,"用户名或密码不能为空");
        }
    }
}
