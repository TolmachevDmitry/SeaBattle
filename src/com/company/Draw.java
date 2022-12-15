package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.company.ModeCreateOwnArmy.*;
import com.company.ModeBeginning.*;

public class Draw extends JFrame {
    private JPanel mainPanel;

    ModeBeginning beginning = new ModeBeginning();
    ModeCreateOwnArmy createArmy = new ModeCreateOwnArmy();

    int[][] shipsOfUser = new int[10][10];
    int[][] shipsOfComputer = new int[10][10];

    private int getInteger(JTextField te) {
        return Integer.parseInt(te.getText());
    }

    public Draw() {
        this.setTitle("FrameMain");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1200, 700);
        this.setVisible(true);

        mainPanel.addMouseListener(new MouseEvents() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + " <---> " + e.getY());
                if (beginning.isActive()) {
                    workWithBeginning(e.getX(), e.getY());
                }
                if (createArmy.isActive()) {
                    workWithCreatingArmy(e.getX(), e.getY());
                }
            }

        });

        mainPanel.addMouseMotionListener(new MouseEvents() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//
//            }
        });

    }

    // Режим начала игры
    public void workWithBeginning(int x, int y) {
        beginning.buttonIsClicked(x, y);

        if (!beginning.isActive()) {
            createArmy.changeStatus();
            callRepaint();
        }
    }

    public void workWithCreatingArmy(int x, int y) {
        if ((992 < x && x < 1191) && (319 < y && y < 378)) {
            createArmy.generateUserArmy();
            callRepaint();
        }
        if ((992 < x && x < 1191) && (420 < y && y < 520)) {
            if (createArmy.armyIsCreated) {
                System.out.print("После нажатия этой кнопки игра будет переходить в режим сражения (боя)");
            }
        }
    }

    // Т.к. в теле условий repaint() не вызывается...
    public void callRepaint() {
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);

        Graphics2D gr = (Graphics2D) g;

        if (beginning.isActive()) {
            beginning.createGraphicInterface(gr);
        }

        if (createArmy.isActive()) {
            createArmy.createGraphicInterface(gr);
        }

    }

}
