package gui;

import javax.swing.*;

public class Look {

    public static void look(int i){
        try {
            switch (i){
                case 1 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel"); break;
                case 2 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel"); break;
                case 3 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel"); break;
                case 4 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"); break;
                case 5 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel"); break;
                case 6 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel"); break;
                case 7 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel"); break;
                case 8 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel"); break;
                case 9 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel"); break;
                case 10 : javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel"); break;
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
