package gui.user;

import gui.Look;
import gui.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User3 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        Main.centerF.setContentPane(Main.centerP);
        Look.look(1);
        Main.centerF.setVisible(false);
        Main.centerF.setVisible(true);
    }
}
