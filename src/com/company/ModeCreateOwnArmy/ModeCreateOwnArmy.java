package com.company.ModeCreateOwnArmy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModeCreateOwnArmy {

    private class Cell {
        public int xLeft;
        public int xRight;
        public int yUpp;
        public int yDown;

        Cell(int x0, int y0, int x1, int y1) {
            xLeft = x0;
            xRight = x1;
            yUpp = y0;
            yDown = y1;
        }
    }

    ArrayList<SeaObjects> uShips = new ArrayList<SeaObjects>();
    ArrayList<SeaObjects> eShips = new ArrayList<SeaObjects>();
    ArrayList<SeaObjects> tObjects = new ArrayList<SeaObjects>();

    public boolean armyIsCreated = false;

    private boolean isActive = false;

    int[][] userShips = new int[10][10];
    int[][] enemyShips = new int[10][10];

    int[][] tShips = new int[10][10];

    // Размер одной клетки на карте - 40 пикселов
    private int mapStartX = 500;
    private int mapStartY = 200;

    private int mapEndX = 900;
    private int mapEndY = 600;

    Image img1 = new ImageIcon("Pictures/Background_for_createSeaArmy.jpg").getImage();
    Image img2 = new ImageIcon("Pictures/Button_PUT.jpg").getImage();
    Image img3 = new ImageIcon("Pictures/Button_TO_BATTLE.jpg").getImage();

    // Отрисовка графического интерфейса
    public void createGraphicInterface(Graphics gr) {
        gr.drawImage(img1, 0, 0, null);

        createWhiteBackground(gr);
        createLines(gr);

        gr.drawImage(img2, 1000, 350, null);
        gr.drawImage(img3, 1000, 450, null);

        if (armyIsCreated) {
            showShips(gr);
        }
    }

    private void createWhiteBackground(Graphics gr) {
        gr.setColor(Color.WHITE);
        gr.fillRect(mapStartX, mapStartY, 400, 400);
        gr.setColor(Color.BLACK);
    }

    private void createLines(Graphics gr) {
        int x0 = mapStartX;
        int y0 = mapStartY;

        for (int i = 0; i < 11; i++) {
            gr.drawLine(mapStartX, y0, mapEndX, y0);
            gr.drawLine(x0, mapStartY, x0, mapEndY);

            x0 += 40;
            y0 += 40;
        }
    }

    public boolean checkCell(int x, int y) {
        if ((x < 0 || x > 9) || (y < 0 || y > 9)) {
            return true;
        }
        if (tShips[y][x] == 3) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkSuitable(int x0, int y0, int countDeck, boolean pose) {
        boolean suitable = true;
        for (int i = 0; i < countDeck; i++) {
            if (!(checkCell(x0, y0) && checkCell(x0 - 1, y0) && checkCell(x0 - 1, y0 + 1)
                    && checkCell(x0 - 1, y0 - 1) && checkCell(x0 + 1, y0) && checkCell(x0 + 1, y0 + 1)
                    && checkCell(x0 + 1, y0 - 1) && checkCell(x0, y0 - 1) && checkCell(x0, y0 + 1))) {
                suitable = false;
                break;
            }
//            System.out.println(countDeck + " " + x0 + " " + y0);
            if (pose) {
                x0 += 1;
            } else {
                y0 += 1;
            }
        }
        return suitable;
    }

    private void putShip(int countDeck, int x, int y, boolean pose) {
        SeaObjects ship = new SeaObjects(countDeck, x, y, pose);
        for (int i = 0; i < countDeck; i++) {
//            System.out.println(countDeck + " " + x + " " + y);
            tShips[y][x] = 3;

            if (pose) {
                x += 1;
            } else {
                y += 1;
            }
        }

        tObjects.add(ship);
    }

    private void search(int countDeck) {
        boolean pose = (((int) (Math.random() * 2)) == 0) ? (false) : (true);
        int x = 0;
        int y = 0;

        boolean founded = false;
        while (!founded) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            if ((pose && x + (countDeck - 1) >= 10) || (!pose && y + (countDeck - 1) >= 10)) {
                continue;
            }
            founded = checkSuitable(x, y, countDeck, pose);
        }

        putShip(countDeck, x, y, pose);
    }

    // Генерация корабликов
    private void generateArmy() {
        tObjects = new ArrayList<SeaObjects>();
        tShips = new int[10][10];

        for (int i = 4; i >= 1; i--) {
            for (int j = 5 - i; j > 0; j--) {
                search(i);
            }
        }

        armyIsCreated = true;
    }

    public void generateUserArmy() {
        generateArmy();
        userShips = tShips;
        uShips = tObjects;
    }

    // Демонстрация сгенерированных корабликов
    public void showShips(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(userShips[i][j] + " ");
            }
            System.out.println();
        }

        g.setColor(Color.GREEN);
        for (int y = 209, i = 0; y <= 569; y += 40, i++) {
            for (int x = 492, j = 0; x < 892; x += 40, j++) {
                if (userShips[i][j] == 3) {
                    g.fillRect(x + 9, y - 9, 40, 40);
                }
            }
        }
        g.setColor(Color.BLACK);
    }

//    public ArrayList<SeaObjects> getUserShips()

    public void changeStatus() {
        isActive = !isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean inCell(int x, int y) {
        if (((mapStartX < x && x < mapEndX) && (mapStartY < y && y > mapEndY))) {
            return true;
        }
        return false;
    }

}
