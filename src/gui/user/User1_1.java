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

                    JOptionPane.showMessageDialog(Main.centerF,"登录成功");

                    userP1_1.setLayout(null);
                    Main.centerF.setContentPane(userP1_1);
                    Look.look(1);

                    new UMB().menuBar.setVisible(true);

                    JLabel title = new JLabel("一大波房屋正向");
                    title.setForeground(Color.red);
                    title.setFont(new Font("宋体",Font.BOLD,40));
                    title.setBounds(250,120,400,60);
                    userP1_1.add(title);

                    JLabel title1 = new JLabel("您袭来！");
                    title1.setForeground(Color.red);
                    title1.setFont(new Font("宋体",Font.BOLD,40));
                    title1.setBounds(250,220,400,60);
                    userP1_1.add(title1);

                    JLabel backgroundPicture = new JLabel();
                    ImageIcon picture = new ImageIcon("d:/j2se/用户背景1_1.jpg");
                    backgroundPicture.setIcon(picture);
                    backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
                    userP1_1.add(backgroundPicture);

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
