package gui.user;

import charactor.House;
import gui.Look;
import gui.Main;
import jdbc.AppointmentDAO;
import jdbc.CollectionDAO;
import jdbc.HouseDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MyCollection implements ActionListener {

    public static List<House> houses = new ArrayList<>();
    public static House h = new House();

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel mCP = new JPanel();
        mCP.setLayout(new BorderLayout());
        Main.centerF.setContentPane(mCP);
        Look.look(1);

        houses = new CollectionDAO().get(User1.tf1.getText());
        Main.search = 3;

        final HouseTableModel htm = new HouseTableModel();
        final JTable table = new JTable(htm);

        JPanel p = new JPanel();
        final JLabel l = new JLabel("暂时未选中条目");
        JButton b1 = new JButton("我还没用");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(b1.getText().equals("收藏")){

                    b1.setText("取消收藏");

                    new HouseDAO().addCollection(h);
                    new CollectionDAO().add(User1.tf1.getText(), h);

                    table.updateUI();

                    JOptionPane.showMessageDialog(Main.centerF, "收藏成功");
                }else{
                    if (b1.getText().equals("取消收藏")){

                        int option = JOptionPane.showConfirmDialog(Main.centerF,"确定要取消收藏？");

                        if(JOptionPane.OK_OPTION == option){
                            b1.setText("收藏");

                            new HouseDAO().deleteCollection(h);
                            new CollectionDAO().delete(User1.tf1.getText(),h);

                            table.updateUI();
                        }
                    }else{
                        JOptionPane.showMessageDialog(Main.centerF,"点了没用");
                    }
                }
            }
        });
        JButton b2 = new JButton("我也还没用");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b2.getText().equals("预约")){

                    b2.setText("已预约，等待受理");

                    new HouseDAO().changeAppointment(h);
                    new AppointmentDAO().add(User1.tf1.getText(), h);

                    table.updateUI();

                    JOptionPane.showMessageDialog(Main.centerF,"预约成功");
                }else {
                    JOptionPane.showMessageDialog(Main.centerF,"点了没用");
                }
            }
        });
        JButton b3 = new JButton("我更没用");
        b3.addActionListener(new HR_1_1AHR_2_1());
        p.add(l);
        p.add(b1);
        p.add(b2);
        p.add(b3);

        JScrollPane sp = new JScrollPane(table);

        // 使用selection监听器来监听table的哪个条目被选中
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    // 当选择了某一行的时候触发该事件
                    public void valueChanged(ListSelectionEvent e) {
                        // 获取哪一行被选中了
                        int row = table.getSelectedRow();
                        House house = htm.houses.get(row);
                        // 更新标签内容
                        l.setText("当前选中的房屋是： " + house.name);
                        if (new CollectionDAO().isCollection(User1.tf1.getText(), house)){
                            b1.setText("取消收藏");
                        }else{
                            b1.setText("收藏");
                        }

                        if (new HouseDAO().isAppointment(house)){
                            b2.setText("预约");
                            b2.setToolTipText("预约之后不能取消，要等待中介受理");
                        }else{
                            if (new AppointmentDAO().isAppointment(User1.tf1.getText(), house)){
                                b2.setText("已预约，等待受理");
                            }else{
                                b2.setText("无法预约");
                            }
                        }

                        b3.setText("评论");

                        h = house;
                    }
                });

        mCP.add(p,BorderLayout.NORTH);
        mCP.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
