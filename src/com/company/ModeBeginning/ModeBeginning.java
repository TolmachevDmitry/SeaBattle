package com.company.ModeBeginning;

import javax.swing.*;
import java.awt.*;

public class ModeBeginning {

    private boolean isActive = true;

    Image img1 = new ImageIcon("Pictures/Background_for_beginning.jpg").getImage();
    Image img2 = new ImageIcon("Pictures/Tittle.png").getImage();
    Image img3 = new ImageIcon("Pictures/Button_startGame.png").getImage();

    // Отрисовка графического интерфейса
    public void createGraphicInterface(Graphics gr) {
        gr.drawImage(img1, 0, 0, null);
        gr.drawImage(img2, 300, -350, null);
        gr.drawImage(img3, 502, 345, null);
    }

    public void buttonIsClicked(int x, int y) {
        if ((502 <= x && x <= 852) && (345 <= y && 506 >= y)) {
            changeStatus();
        }
    }

    public void changeStatus() {
        isActive = !isActive;
    }

    public boolean isActive() {
        return isActive;
    }

}
