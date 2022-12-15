package com.company.ModeCreateOwnArmy;


// Координаты корабля
class SeaObjects {
    public int[] x;
    public int[] y;
    public int countDeck;
    public boolean poseIsHorizontal;

    public SeaObjects(int countDeck, int x0, int y0, boolean pose) {
        x = new int[countDeck];
        y = new int[countDeck];
        for (int i = 0; i < countDeck; i++) {
            if (pose) {
                x[i] = x0 + i;
                y[i] = y0;
            } else {
                x[i] = x0;
                y[i] = y0 + i;
            }
        }
    }

    public void horizontalShift1(int k) {
        for (int i = 0; i < countDeck; i++) {
            x[i] += k;
        }
    }

    public void horizontalShift(int k) {
        if (0 < x[0] && x[x.length - 1] < 9) {
            horizontalShift1(k);
        }
    }

    public void verticalShift1(int k) {
        for (int i = 0; i < countDeck; i++) {
            y[i] += k;
        }
    }

    public void verticalShift(int k) {
        if (0 < y[0] && y[y.length - 1] < 9) {
            verticalShift1(k);
        }
    }

    public void coup1(int kx, int ky) {
        int x0 = x[0];
        int y0 = y[0];
        for (int i = 0; i < countDeck; i++) {
            x[i] = x0 + i * kx;
            y[i] = y0 + i * ky;
        }
    }

    public void coup() {
        if (poseIsHorizontal) {
            // Располагаем кораблик по вертикали
            if (y[0] + (countDeck - 1) >= 10) {
                // Наклон правого конца
                coup1(0, 1);
            } else if (y[0] - (countDeck - 1) >= 0) {
                // Вверх
                coup1(0, -1);
            }
        } else {
            // Располагаем кораблик по горизонтали
            if (x[0] + (countDeck - 1) <= 10) {
                // Наклон вправо
                coup1(1, 0);
            } else if (x[0] + (countDeck - 1) >= 0) {
                // Влево (Если вправо - не можем)
                coup1(-1, 0);
            }
        }
    }
}
