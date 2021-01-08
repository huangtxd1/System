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
        JButton b = new JButton("我还没用");
        tf.setPreferredSize(new Dimension(300,30));
        p.add(b);
        p.add(tf);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (b.getText().equals("我还没用")){
                    JOptionPane.showMessageDialog(Main.centerF,"点了没用");
                    return;
                }
                if (b.getText().equals("已回复")){
                    JOptionPane.showMessageDialog(Main.centerF,"已回复");
                    return;
                }
                if (b.getText().equals("回复")){

                    if (tf.getText().equals("")){
                        JOptionPane.showMessageDialog(Main.centerF,"请回复");
                        return;
                    }

                    int index = table.getSelectedRow();
                    if (-1 == index){
                        JOptionPane.showMessageDialog(Main.centerF,"回复前请手动选择一行");
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

                b.setText("回复");
                b.setToolTipText("不能超过三十个字");

                d = discuss;
            }
        });

        aUP.add(p,BorderLayout.NORTH);
        aUP.add(sp,BorderLayout.CENTER);

        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
