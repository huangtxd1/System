package gui.user;

import charactor.House;
import gui.Look;
import gui.Main;
import jdbc.AppointmentDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MyAppointment implements ActionListener {

    public static List<House> houses = new ArrayList<>();
    public static House h = new House();

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel mAP = new JPanel();
        mAP.setLayout(new BorderLayout());
        Main.centerF.setContentPane(mAP);
        Look.look(1);

        houses = new AppointmentDAO().get(User1.tf1.getText());
        Main.search = 4;

        final HouseTableModel htm = new HouseTableModel();
        final JTable table = new JTable(htm);

        JPanel p = new JPanel();
        final JLabel l = new JLabel("暂时未选中条目");
        JButton b1 = new JButton("我还没用");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (b1.getText().equals("我还没用")){

                    JOptionPane.showMessageDialog(Main.centerF,"点了没用");
                }else{

                    JDialog d = new JDialog(Main.centerF);
                    d.setModal(true);
                    d.setTitle("请支付");
                    d.setLocation(200,200);
                    d.setSize(800,600);
                    d.setLayout(null);

                    JLabel backgroundPicture = new JLabel();
                    ImageIcon picture = new ImageIcon("d:/j2se/支付.jpg");
                    backgroundPicture.setIcon(picture);
                    backgroundPicture.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
                    d.add(backgroundPicture);

                    d.setVisible(true);
                }
            }
        });
        p.add(l);
        p.add(b1);

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
                        b1.setText("支付");

                        h = house;
                    }
                });

        mAP.add(p,BorderLayout.NORTH);
        mAP.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
