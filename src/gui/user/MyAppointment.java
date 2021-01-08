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
        final JLabel l = new JLabel("��ʱδѡ����Ŀ");
        JButton b1 = new JButton("�һ�û��");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (b1.getText().equals("�һ�û��")){

                    JOptionPane.showMessageDialog(Main.centerF,"����û��");
                }else{

                    JDialog d = new JDialog(Main.centerF);
                    d.setModal(true);
                    d.setTitle("��֧��");
                    d.setLocation(200,200);
                    d.setSize(800,600);
                    d.setLayout(null);

                    JLabel backgroundPicture = new JLabel();
                    ImageIcon picture = new ImageIcon("d:/j2se/֧��.jpg");
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
                        b1.setText("֧��");

                        h = house;
                    }
                });

        mAP.add(p,BorderLayout.NORTH);
        mAP.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
