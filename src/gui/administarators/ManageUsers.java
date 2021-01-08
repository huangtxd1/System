package gui.administarators;

import charactor.User;
import gui.Look;
import gui.Main;

import gui.user.User1_1;
import jdbc.AppointmentDAO;
import jdbc.CollectionDAO;
import jdbc.UserOrAdministratorsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUsers implements ActionListener {

    static UserTableModel utm = new UserTableModel();
    static JTable t = new JTable(utm);

    // 把分页按钮放在这里，后面监听器好访问
    static JButton bFirst = new JButton("首页");
    static JButton bPre = new JButton("上一页");
    static JButton bNext = new JButton("下一页");
    static JButton bLast = new JButton("末页");

    static int number = 10;// 每页显示10个
    static int start = 0;// 开始的页码

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel mUP = new JPanel();
        mUP.setLayout(null);
        Main.centerF.setContentPane(mUP);
        Look.look(1);

        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.getSelectionModel().setSelectionInterval(0,0);

        JPanel pOperation = new JPanel();
        JButton bAdd = new JButton("增加");
        JButton bDelete = new JButton("删除");
        JButton bEdit = new JButton("修改价格");
        pOperation.add(bAdd);
        pOperation.add(bDelete);
        pOperation.add(bEdit);

        JPanel pPage = new JPanel();
        pPage.add(bFirst);
        pPage.add(bPre);
        pPage.add(bNext);
        pPage.add(bLast);

        bEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = t.getSelectedRow();
                if(index == -1){
                    JOptionPane.showMessageDialog(Main.centerF,"修改前需要先选中一行");
                    return;
                }

                User user = utm.users.get(index);

                EditDialog ed = new EditDialog(Main.centerF);
                ed.tfPassword.setText(String.valueOf(user.password));

                ed.setVisible(true);
            }
        });

        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDialog(Main.centerF).setVisible(true);
                updateButtonStatus();
            }
        });
        bDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int index = t.getSelectedRow();
                if(-1 == index) {
                    JOptionPane.showMessageDialog(Main.centerF,"删除前需要先选中一行");
                    return;
                }

                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(Main.centerF,"确认删除？"))
                    return;

                User user = utm.users.get(index);
                int id = user.id;

                new CollectionDAO().delete(user);
                new AppointmentDAO().delete(user);
                new UserOrAdministratorsDAO().delete(id);

                start = 0;

                updateTable();
                updateButtonStatus();
            }
        });

        addPageListener();

        JScrollPane sp = new JScrollPane(t);

        sp.setBounds(0,30,800,240);
        pOperation.setBounds(0,270,800,60);
        pPage.setBounds(0,360,800,240);
        mUP.add(sp);
        mUP.add(pOperation);
        mUP.add(pPage);
        updateButtonStatus();

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }

    private static void addPageListener() {

        bFirst.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start = 0;
                updateTable();
                updateButtonStatus();
            }
        });
        bPre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start -= number;
                updateTable();
                updateButtonStatus();
            }
        });
        bNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start += number;
                updateTable();
                updateButtonStatus();
            }
        });
        bLast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start = last();
                updateTable();
                updateButtonStatus();
            }
        });
    }

    private static void updateButtonStatus(){
        int last = last();

        bFirst.setEnabled(false);
        bPre.setEnabled(false);
        if (0 != start){
            bFirst.setEnabled(true);
            bPre.setEnabled(true);
        }

        if(0 == start){
            bFirst.setEnabled(false);
            bFirst.setEnabled(false);
        }

        if (start == last) {
            bLast.setEnabled(false);
            bNext.setEnabled(false);
        }

        if (start < last){
            bLast.setEnabled(true);
            bNext.setEnabled(true);
        }
    }

    static class AddDialog extends JDialog {

        JLabel lUserName = new JLabel("用户名");
        JLabel lUserPassword = new JLabel("密码");

        JTextField tfUserName = new JTextField();
        JTextField tfUserPassword = new JTextField();

        JButton bSubmit = new JButton("提交");

        AddDialog(JFrame f){
            super(f);
            this.setModal(true);
            int gap = 50;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(2,2,gap,gap));
            pInput.add(lUserName);
            pInput.add(tfUserName);
            pInput.add(lUserPassword);
            pInput.add(tfUserPassword);

            pSubmit.add(bSubmit);

            pInput.setBounds(50, 20, 200, 100);
            pSubmit.setBounds(0, 130, 300, 150);

            this.add(pInput);
            this.add(pSubmit);

            this.setSize(300,200);
            this.setLocationRelativeTo(f);
            bSubmit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (User1_1.isNumericOrLetter(tfUserName.getText()) && User1_1.isNumericOrLetter(tfUserPassword.getText())){

                        String userName = tfUserName.getText();
                        String userPassword = tfUserPassword.getText();

                        User user = new User();
                        user.name = userName;
                        user.password = userPassword;

                        new UserOrAdministratorsDAO().add(user);

                        JOptionPane.showMessageDialog(f, "提交成功");

                        ManageUsers.AddDialog.this.setVisible(false);
                        start = 0;
                        updateTable();
                    }else {
                        JOptionPane.showMessageDialog(Main.centerF,"用户名和密码只能是字母或数字");
                    }
                }
            });
        }
    }

    public static void updateTable() {
        utm.users = new UserOrAdministratorsDAO().list(start,number);
        t.updateUI();
        if (!utm.users.isEmpty()){
            t.getSelectionModel().setSelectionInterval(0,0);
        }
    }

    static class EditDialog extends JDialog{

        JLabel lPassword = new JLabel("密码");

        JTextField tfPassword = new JTextField();

        JButton bSubmit = new JButton("提交");

        EditDialog(JFrame f){

            super(f);
            this.setModal(true);
            int gap = 50;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(1,2,gap,gap));
            pInput.add(lPassword);
            pInput.add(tfPassword);

            pSubmit.add(bSubmit);

            pInput.setBounds(50,20,200,50);
            pSubmit.setBounds(0,130,300,150);

            this.add(pInput);
            this.add(pSubmit);

            this.setSize(300,200);
            this.setLocationRelativeTo(f);

            bSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(User1_1.isNumericOrLetter(tfPassword.getText())){

                        int index = t.getSelectedRow();
                        int id = utm.users.get(index).id;

                        int password = Integer.parseInt(tfPassword.getText());

                        User user = new User();
                        user.id = id;
                        user.name = utm.users.get(index).name;
                        user.password = utm.users.get(index).password;

                        new UserOrAdministratorsDAO().update(user);

                        JOptionPane.showMessageDialog(f,"提交成功");

                        ManageUsers.EditDialog.this.setVisible(false);
                        updateTable();
                    }else{
                        JOptionPane.showMessageDialog(Main.centerF,"价格只能是数字");
                    }
                }
            });
        }
    }

    private static int last(){

        int last;

        int total = new UserOrAdministratorsDAO().getTotal();

        if(0 == total % number) {
            last = total - number;
        }else {
            last = total - total % number;
        }
        return last;
    }
}
