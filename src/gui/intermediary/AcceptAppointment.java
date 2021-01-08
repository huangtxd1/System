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
        final JLabel l = new JLabel("��ʱδѡ����Ŀ");
        JButton b1 = new JButton("�һ�û��");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.getText().equals("�һ�û��")||b1.getText().equals("������ԤԼ")){

                    JOptionPane.showMessageDialog(Main.centerF,"����û��");
                }else {

                    int option = JOptionPane.showConfirmDialog(Main.centerF, "ȷ��Ҫ���������û�" + new AppointmentDAO().appointmentMan(h) + "��ԤԼ");
                    if (JOptionPane.OK_OPTION == option){
                        new HouseDAO().changeAppointment(h);
                        new AppointmentDAO().delete(h);
                        b1.setText("������ԤԼ");
                        table.updateUI();
                    }
                }
            }
        });
        p.add(l);
        p.add(b1);

        JScrollPane sp = new JScrollPane(table);

        // ʹ��selection������������table���ĸ���Ŀ��ѡ��
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    // ��ѡ����ĳһ�е�ʱ�򴥷����¼�
                    public void valueChanged(ListSelectionEvent e) {
                        // ��ȡ��һ�б�ѡ����
                        int row = table.getSelectedRow();
                        House house = htm.houses.get(row);
                        // ���±�ǩ����
                        l.setText("��ǰѡ�еķ����ǣ� " + house.name);
                        if (new HouseDAO().isAppointment(house)){
                            b1.setText("������ԤԼ");
                        }else{
                            b1.setText("����ԤԼ");
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
