package gui.intermediary;

import charactor.House;
import gui.Look;
import gui.Main;
import gui.user.HR_1;
import gui.user.HouseTableModel;
import jdbc.AppointmentDAO;
import jdbc.CollectionDAO;
import jdbc.HouseDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHouse implements ActionListener {

    static {
        Main.search = 5;
    }
    static HouseTableModel htm = new HouseTableModel();
    static JTable t = new JTable(htm);

    // 把分页按钮放在这里，后面监听器好访问
    static JButton bFirst = new JButton("首页");
    static JButton bPre = new JButton("上一页");
    static JButton bNext = new JButton("下一页");
    static JButton bLast = new JButton("末页");

    static int number = 10;// 每页显示10个
    static int start = 0;// 开始的页码

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("点击");
        JPanel aHP = new JPanel();
        aHP.setLayout(null);
        Main.centerF.setContentPane(aHP);
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

                House house = htm.houses.get(index);

                EditDialog ed = new EditDialog(Main.centerF);
                ed.tfPrice.setText(String.valueOf(house.price));

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

                House house = htm.houses.get(index);
                int id = house.id;

                new CollectionDAO().delete(house);
                new AppointmentDAO().delete(house);
                new HouseDAO().delete(id);

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
        aHP.add(sp);
        aHP.add(pOperation);
        aHP.add(pPage);
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
        JLabel lName = new JLabel("名称");
        JLabel lArea = new JLabel("面积");
        JLabel lPlace = new JLabel("地点");
        JLabel lPrice = new JLabel("价格");
        JLabel lFormat = new JLabel("样式");

        JTextField tfName = new JTextField();
        JTextField tfArea = new JTextField();
        JComboBox tfPlace = new JComboBox(new String[] {"永定区", "武陵源区", "慈利县", "桑植县"});
        JTextField tfPrice = new JTextField();
        JComboBox tfFormat = new JComboBox(new String[] {"一室", "一室一厅", "两室一厅", "两室两厅", "三室一厅", "三室两厅", "四室一厅", "四室两厅", "五室一厅", "五室两厅","五室三厅"});

        JButton bSubmit = new JButton("提交");

        AddDialog(JFrame f){
            super(f);
            this.setModal(true);
            int gap = 50;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(5,2,gap,gap));
            pInput.add(lName);
            pInput.add(tfName);
            pInput.add(lArea);
            pInput.add(tfArea);
            pInput.add(lPlace);
            pInput.add(tfPlace);
            pInput.add(lPrice);
            pInput.add(tfPrice);
            pInput.add(lFormat);
            pInput.add(tfFormat);

            pSubmit.add(bSubmit);

            pInput.setBounds(200,20,200,300);
            pSubmit.setBounds(150,330,300,150);

            this.add(pInput);
            this.add(pSubmit);

            this.setSize(600,480);
            this.setLocationRelativeTo(f);
            bSubmit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (HR_1.isNumeric(tfArea.getText())&&HR_1.isNumeric(tfPrice.getText())){

                        String name = tfName.getText();
                        int area = Integer.parseInt(tfArea.getText());
                        String place = String.valueOf(tfPlace.getSelectedItem());
                        int price = Integer.parseInt(tfPrice.getText());
                        String format = String.valueOf(tfFormat.getSelectedItem());

                        House h = new House();
                        h.name = name;
                        h.area = area;
                        h.place = place;
                        h.price = price;
                        h.format = format;

                        new HouseDAO().add(h);

                        JOptionPane.showMessageDialog(f, "提交成功");

                        AddDialog.this.setVisible(false);
                        start = 0;
                        updateTable();
                    }else {
                        JOptionPane.showMessageDialog(Main.centerF,"面积和价格只能是数字");
                    }
                }
            });
        }
    }

    public static void updateTable() {
        htm.houses = new HouseDAO().list(start,number);
        t.updateUI();
        if (!htm.houses.isEmpty()){
            t.getSelectionModel().setSelectionInterval(0,0);
        }
    }

    static class EditDialog extends JDialog{

        JLabel lPrice = new JLabel("价格");

        JTextField tfPrice = new JTextField();

        JButton bSubmit = new JButton("提交");

        EditDialog(JFrame f){

            super(f);
            this.setModal(true);
            int gap = 50;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(1,2,gap,gap));
            pInput.add(lPrice);
            pInput.add(tfPrice);

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
                    if(HR_1.isNumeric(tfPrice.getText())){

                        int index = t.getSelectedRow();
                        int id = htm.houses.get(index).id;

                        int price = Integer.parseInt(tfPrice.getText());

                        House h = new House();
                        h.id = id;
                        h.name = htm.houses.get(index).name;
                        h.area = htm.houses.get(index).area;
                        h.place = htm.houses.get(index).place;
                        h.price = price;
                        h.format = htm.houses.get(index).format;

                        new HouseDAO().update(h);

                        JOptionPane.showMessageDialog(f,"提交成功");

                        EditDialog.this.setVisible(false);
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

        int total = new HouseDAO().getTotal();

        if(0 == total % number) {
            last = total - number;
        }else {
            last = total - total % number;
        }
        return last;
    }
}
