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

    // �ѷ�ҳ��ť�����������������÷���
    static JButton bFirst = new JButton("��ҳ");
    static JButton bPre = new JButton("��һҳ");
    static JButton bNext = new JButton("��һҳ");
    static JButton bLast = new JButton("ĩҳ");

    static int number = 10;// ÿҳ��ʾ10��
    static int start = 0;// ��ʼ��ҳ��

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("���");
        JPanel aHP = new JPanel();
        aHP.setLayout(null);
        Main.centerF.setContentPane(aHP);
        Look.look(1);

        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.getSelectionModel().setSelectionInterval(0,0);

        JPanel pOperation = new JPanel();
        JButton bAdd = new JButton("����");
        JButton bDelete = new JButton("ɾ��");
        JButton bEdit = new JButton("�޸ļ۸�");
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
                    JOptionPane.showMessageDialog(Main.centerF,"�޸�ǰ��Ҫ��ѡ��һ��");
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
                    JOptionPane.showMessageDialog(Main.centerF,"ɾ��ǰ��Ҫ��ѡ��һ��");
                    return;
                }

                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(Main.centerF,"ȷ��ɾ����"))
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
        JLabel lName = new JLabel("����");
        JLabel lArea = new JLabel("���");
        JLabel lPlace = new JLabel("�ص�");
        JLabel lPrice = new JLabel("�۸�");
        JLabel lFormat = new JLabel("��ʽ");

        JTextField tfName = new JTextField();
        JTextField tfArea = new JTextField();
        JComboBox tfPlace = new JComboBox(new String[] {"������", "����Դ��", "������", "ɣֲ��"});
        JTextField tfPrice = new JTextField();
        JComboBox tfFormat = new JComboBox(new String[] {"һ��", "һ��һ��", "����һ��", "��������", "����һ��", "��������", "����һ��", "��������", "����һ��", "��������","��������"});

        JButton bSubmit = new JButton("�ύ");

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

                        JOptionPane.showMessageDialog(f, "�ύ�ɹ�");

                        AddDialog.this.setVisible(false);
                        start = 0;
                        updateTable();
                    }else {
                        JOptionPane.showMessageDialog(Main.centerF,"����ͼ۸�ֻ��������");
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

        JLabel lPrice = new JLabel("�۸�");

        JTextField tfPrice = new JTextField();

        JButton bSubmit = new JButton("�ύ");

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

                        JOptionPane.showMessageDialog(f,"�ύ�ɹ�");

                        EditDialog.this.setVisible(false);
                        updateTable();
                    }else{
                        JOptionPane.showMessageDialog(Main.centerF,"�۸�ֻ��������");
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
