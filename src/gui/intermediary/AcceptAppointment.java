package gui.intermediary;

import charactor.House;
import gui.Look;
import gui.Main;
import gui.user.HouseTableModel;
import jdbc.AppointmentDAO;
import jdbc.HouseDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AcceptAppointment implements ActionListener {

    public static List<House> houses = new ArrayList<>();
    public static House h = new House();

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel aAP = new JPanel();
        aAP.setLayout(new BorderLayout());
        Main.centerF.setContentPane(aAP);
        Look.look(1);

        houses = new HouseDAO().getAppointment();
        Main.search = 6;

        final HouseTableModel htm = new HouseTableModel();
        final JTable table = new JTable(htm);

        JPanel p = new JPanel();
        final JLabel l = new JLabel("暂时未选中条目");
        JButton b1 = new JButton("我还没用");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.getText().equals("我还没用")||b1.getText().equals("已受理预约")){

                    JOptionPane.showMessageDialog(Main.centerF,"点了没用");
                }else {

                    int option = JOptionPane.showConfirmDialog(Main.centerF, "确定要受理来自用户" + new AppointmentDAO().appointmentMan(h) + "的预约");
                    if (JOptionPane.OK_OPTION == option){
                        new HouseDAO().changeAppointment(h);
                        new AppointmentDAO().delete(h);
                        b1.setText("已受理预约");
                        table.updateUI();
                    }
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
                        if (new HouseDAO().isAppointment(house)){
                            b1.setText("已受理预约");
                        }else{
                            b1.setText("受理预约");
                        }

                        h = house;
                    }
                });

        aAP.add(p,BorderLayout.NORTH);
        aAP.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
