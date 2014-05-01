package io.github.lucariatias.harmonicmoon;

import javax.swing.*;
import java.awt.*;

public class HarmonicMoonFrame extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException exception) {
            exception.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new HarmonicMoonFrame();
                frame.setVisible(true);
            }
        });
    }

    public HarmonicMoonFrame() {
        add(new HarmonicMoon(this));
        setTitle("Harmonic Moon");
        setResizable(false);
        setFocusable(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
