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

        JOptionPane.showMessageDialog(Main.centerF,"搜索成功");

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

                        if(JOptionPane.OK_OPTION == option) {
                            b1.setText("收藏");

                            new HouseDAO().deleteCollection(h);
                            new CollectionDAO().delete(User1.tf1.getText(), h);

                            table.updateUI();
                        }
                    }else{
                        JOptionPane.showMessageDialog(Main.centerF,"点了没用");
                    }
                }
            }
        });
        JButton b2 = new JButton("我也还没用");
        b2.addActionListener(new HR_1_1AHR_2_1());
        p.add(l);
        p.add(b1);
        p.add(b2);

        JScrollPane sp = new JScrollPane(table);

        // 使用selection监听器来监听table的哪个条目被选中
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    // 当选择了某一行的时候触发该事件
                    public void valueChanged(ListSelectionEvent e) {
                        // 获取哪一行被选中了
                        int row = table.getSelectedRow();
                        // 根据选中的行，到HeroTableModel中获取对应的对象
                        House house = htm.houses.get(row);
                        // 更新标签内容
                        l.setText("当前选中的房屋是： " + house.name);
                        if (new CollectionDAO().isCollection(User1.tf1.getText(), house)){
                            b1.setText("取消收藏");
                        }else{
                            b1.setText("收藏");
                        }
                        b2.setText("评论");

                        h = house;
                    }
                });

        hRP_2_1.add(p,BorderLayout.NORTH);
        hRP_2_1.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
