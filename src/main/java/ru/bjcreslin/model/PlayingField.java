package ru.bjcreslin.model;

import lombok.Getter;
import lombok.Setter;


/**
 * Класс игрового поля.
 */
//Генерируется комната, в ней случайно расставляются роботы, золото, дырки в полу и игрок.


public class PlayingField {
    private int nSize;

    @Getter
    @Setter
    private StaticAble[][] playingFieldCells; // Само игровое поле

    PlayingField(int nSize) {
        this.nSize = nSize;
        playingFieldCells = new StaticAble[nSize][nSize];
        fillAllCellsGround();
    }

    /**
     * выдает ячейку по координатам
     *
     * @param x координата
     * @param y координата
     * @return ячейка
     */
    public StaticAble getCell(int x, int y) {
        return playingFieldCells[y][x];
    }


    /**
     * Заполняем все клетки "землей"
     */
    private void fillAllCellsGround() {
        for (int i = 0; i < nSize; i++) {
            for (int j = 0; j < nSize; j++) {
                setGround(i, j);
            }
        }
    }

    void setCell(int x, int y, StaticAble staticAble) {
        playingFieldCells[y][x] = staticAble;
    }

    /**Заполняем текущую клетку "землей"*/
    void setGround(int xtemp, int ytemp) {
        playingFieldCells[ytemp][xtemp] = Ground.getInstance();
    }


}

