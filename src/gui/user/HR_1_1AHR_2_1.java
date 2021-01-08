package gui.user;

import charactor.Discuss;
import gui.Main;
import jdbc.DiscussDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HR_1_1AHR_2_1 implements ActionListener {

    public static List<Discuss> discusses = new ArrayList<>();
    public static int houseId;

    @Override
    public void actionPerformed(ActionEvent e) {

        JDialog jDialog = new JDialog(Main.centerF);
        jDialog.setModal(true);
        jDialog.setTitle("评论");
        jDialog.setLocation(200,200);
        jDialog.setSize(800,600);
        jDialog.setLayout(new BorderLayout());

        if (Main.search == 1){
            discusses = new DiscussDAO().houseIDGet(HR_1_1.h.id);
            houseId = HR_1_1.h.id;
        }else{
            if (Main.search == 2){
                discusses = new DiscussDAO().houseIDGet(HR_2_1.h.id);
                houseId = HR_2_1.h.id;
            }else{
                discusses = new DiscussDAO().houseIDGet(MyCollection.h.id);
                houseId = MyCollection.h.id;
            }
        }

        final DiscussTableModel dtm = new DiscussTableModel();
        final JTable table = new JTable(dtm);

        JPanel p = new JPanel();
        final JButton b = new JButton("发表评论：");
        b.setToolTipText("不能超过三十字");
        final JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(400,30));
        p.add(b);
        p.add(tf);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DiscussDAO dao = new DiscussDAO();

                Discuss discuss_1 = new Discuss(houseId, User1.tf1.getText(), tf.getText());

                dao.add(discuss_1);

                dtm.discusses = dao.houseIDGet(houseId);

                tf.setText("");

                table.updateUI();
            }
        });

        JScrollPane sp = new JScrollPane(table);

        jDialog.add(p, BorderLayout.NORTH);
        jDialog.add(sp, BorderLayout.CENTER);

        if (houseId == 0){
            jDialog.setVisible(false);
            JOptionPane.showMessageDialog(Main.centerF,"点了没用");
        }else{
            jDialog.setVisible(true);
        }
    }
}
