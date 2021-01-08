package gui.user;

import charactor.House;
import gui.Look;
import gui.Main;
import jdbc.CollectionDAO;
import jdbc.HouseDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HR_2_1 implements ActionListener {

    public static List<House> houses = new ArrayList<>();
    public static House h = new House();

    @Override
    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(Main.centerF,"�����ɹ�");

        JPanel hRP_2_1 = new JPanel();
        hRP_2_1.setLayout(new BorderLayout());
        Main.centerF.setContentPane(hRP_2_1);
        Look.look(1);

        HouseDAO houseDAO = new HouseDAO();
        houses = houseDAO.list(String.valueOf(HR_2.textField.getText()));
        Main.search = 2;

        final HouseTableModel htm = new HouseTableModel();
        final  JTable table = new JTable(htm);

        JPanel p = new JPanel();
        final JLabel l = new JLabel("��ʱδѡ����Ŀ");
        JButton b1 = new JButton("�һ�û��");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(b1.getText().equals("�ղ�")){

                    b1.setText("ȡ���ղ�");

                    new HouseDAO().addCollection(h);
                    new CollectionDAO().add(User1.tf1.getText(), h);

                    table.updateUI();

                    JOptionPane.showMessageDialog(Main.centerF, "�ղسɹ�");
                }else{
                    if (b1.getText().equals("ȡ���ղ�")){

                        int option = JOptionPane.showConfirmDialog(Main.centerF,"ȷ��Ҫȡ���ղأ�");

                        if(JOptionPane.OK_OPTION == option) {
                            b1.setText("�ղ�");

                            new HouseDAO().deleteCollection(h);
                            new CollectionDAO().delete(User1.tf1.getText(), h);

                            table.updateUI();
                        }
                    }else{
                        JOptionPane.showMessageDialog(Main.centerF,"����û��");
                    }
                }
            }
        });
        JButton b2 = new JButton("��Ҳ��û��");
        b2.addActionListener(new HR_1_1AHR_2_1());
        p.add(l);
        p.add(b1);
        p.add(b2);

        JScrollPane sp = new JScrollPane(table);

        // ʹ��selection������������table���ĸ���Ŀ��ѡ��
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    // ��ѡ����ĳһ�е�ʱ�򴥷����¼�
                    public void valueChanged(ListSelectionEvent e) {
                        // ��ȡ��һ�б�ѡ����
                        int row = table.getSelectedRow();
                        // ����ѡ�е��У���HeroTableModel�л�ȡ��Ӧ�Ķ���
                        House house = htm.houses.get(row);
                        // ���±�ǩ����
                        l.setText("��ǰѡ�еķ����ǣ� " + house.name);
                        if (new CollectionDAO().isCollection(User1.tf1.getText(), house)){
                            b1.setText("ȡ���ղ�");
                        }else{
                            b1.setText("�ղ�");
                        }
                        b2.setText("����");

                        h = house;
                    }
                });

        hRP_2_1.add(p,BorderLayout.NORTH);
        hRP_2_1.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
