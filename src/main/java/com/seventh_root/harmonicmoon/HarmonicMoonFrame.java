package com.seventh_root.harmonicmoon;

import javax.swing.*;
import java.awt.*;

public class HarmonicMoonFrame extends JFrame {

    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException exception) {
            exception.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new HarmonicMoonFrame(args);
                frame.setVisible(true);
            }
        });
    }

    public HarmonicMoonFrame(String[] args) {
        long startTime = System.currentTimeMillis();
        HarmonicMoon harmonicMoon = new HarmonicMoon(this, args);
        add(harmonicMoon);
        harmonicMoon.getLogger().info("Set up game (" + (System.currentTimeMillis() - startTime) + "ms)");
        setTitle("Harmonic Moon");
        setResizable(false);
        setFocusable(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
