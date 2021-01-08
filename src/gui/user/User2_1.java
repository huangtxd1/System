package gui.user;

import charactor.User;
import gui.Main;
import jdbc.UserOrAdministratorsDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User2_1 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!User2.tf1.getText().equals("")&&!User2.tf2.getText().equals("")){

            UserOrAdministratorsDAO userOrAdministratorsDAO = new UserOrAdministratorsDAO();

            if(User1_1.isNumericOrLetter(User2.tf1.getText())&&User1_1.isNumericOrLetter(User2.tf2.getText())){
                if(userOrAdministratorsDAO.duplicateChecking(User2.tf1.getText())){

                    User user = new User(User2.tf1.getText(),User2.tf2.getText());
                    userOrAdministratorsDAO.add(user);
                    JOptionPane.showMessageDialog(Main.centerF,"注册成功");
                }else{
                    JOptionPane.showMessageDialog(Main.centerF,"用户名已存在");
                }
            }else{
                JOptionPane.showMessageDialog(Main.centerF,"用户名或密码格式不正确");
            }
        }else{
            JOptionPane.showMessageDialog(Main.centerF,"用户名或密码不能为空");
        }
    }
}
