package gui.intermediary;

import charactor.Discuss;
import gui.Look;
import gui.Main;
import jdbc.DiscussDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AnswerUser implements ActionListener {

    public static List<Discuss> discusses = new ArrayList<>();
    public static Discuss d = new Discuss();
    public static JTextField tf = new JTextField();

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel aUP = new JPanel();
        aUP.setLayout(new BorderLayout());
        Main.centerF.setContentPane(aUP);
        Look.look(1);

        discusses = new DiscussDAO().getAll();

        //System.out.println(discusses.size());

        final DiscussTableModel_a dtm_a = new DiscussTableModel_a();
        final JTable table = new JTable(dtm_a);

        JPanel p = new JPanel();
        JButton b = new JButton("�һ�û��");
        tf.setPreferredSize(new Dimension(300,30));
        p.add(b);
        p.add(tf);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (b.getText().equals("�һ�û��")){
                    JOptionPane.showMessageDialog(Main.centerF,"����û��");
                    return;
                }
                if (b.getText().equals("�ѻظ�")){
                    JOptionPane.showMessageDialog(Main.centerF,"�ѻظ�");
                    return;
                }
                if (b.getText().equals("�ظ�")){

                    if (tf.getText().equals("")){
                        JOptionPane.showMessageDialog(Main.centerF,"��ظ�");
                        return;
                    }

                    int index = table.getSelectedRow();
                    if (-1 == index){
                        JOptionPane.showMessageDialog(Main.centerF,"�ظ�ǰ���ֶ�ѡ��һ��");
                    }

                    new DiscussDAO().addAnswer(d,tf.getText());

                    tf.setText("");

                    dtm_a.discusses = new DiscussDAO().getAll();

                    table.updateUI();

                    table.setRowSelectionInterval(0,0);
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int row = table.getSelectedRow();
                Discuss discuss = dtm_a.discusses.get(row);

                b.setText("�ظ�");
                b.setToolTipText("���ܳ�����ʮ����");

                d = discuss;
            }
        });

        aUP.add(p,BorderLayout.NORTH);
        aUP.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
