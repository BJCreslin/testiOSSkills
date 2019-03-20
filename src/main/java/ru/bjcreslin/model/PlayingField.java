package ru.bjcreslin.model;

import lombok.Data;

/**
 * Класс игрового поля.
 */
//Генерируется комната, в ней случайно расставляются роботы, золото, дырки в полу и игрок.

@Data

public class PlayingField {
    private static int nSize;
    private static GameObject[][] playingFieldCells; // Само игровое поле

    public PlayingField(int nSize) {
        this.nSize = nSize;
        playingFieldCells = new GameObject[nSize][nSize];

    }

    //Заполняем все клетки "землей"
    public void fillAllCellsGround() {
        for (int i = 0; i < nSize; i++) {
            for (int j = 0; j < nSize; j++) {
                playingFieldCells[i][j] = new Ground();
            }
        }
    }

//ПРоверяем нахождение объекта Movable в пределах игрового поля
    public static boolean isObjectInField(Movable movableObject) {
        return ((movableObject.getX() >= 0) & (movableObject.getY() >= 0) &
                (movableObject.getX() < nSize) & (movableObject.getY() < nSize)) ? true : false;
    }

    //Проверяем, находится ли по координатам объекта movable куча золота
    public static boolean isObjectInGold(Movable movable) {
        return playingFieldCells[movable.getY()][movable.getX()].getClass().isInstance(PieceOfGold.class);
    }

    //Проверяем, находится ли по координатам объекта movable дыра
    public static boolean isObjectInHole(Movable movable) {
        return playingFieldCells[movable.getY()][movable.getX()].getClass().isInstance(Hole.class);
    }

}
