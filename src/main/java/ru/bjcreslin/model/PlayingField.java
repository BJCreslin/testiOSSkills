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

    public PlayingField(int nSize) {
        this.nSize = nSize;
        playingFieldCells = new StaticAble[nSize][nSize];
    }

    public StaticAble getCell(int x, int y) {
        return playingFieldCells[y][x];
    }


    //Заполняем все клетки "землей"
    public void fillAllCellsGround() {
        for (int i = 0; i < nSize; i++) {
            for (int j = 0; j < nSize; j++) {
                setGround(i, j);
            }
        }
    }

    public void setCell(int x, int y, StaticAble staticAble) {
        playingFieldCells[y][x] = staticAble;
    }

    //Заполняем текущую клетку "землей"
    public void setGround(int xtemp, int ytemp) {
        playingFieldCells[ytemp][xtemp] = Ground.getInstance();
    }


    //ПРоверяем нахождение объекта Movable в пределах игрового поля
//    public static boolean isObjectInField(Movable movableObject) {
//        return ((movableObject.getX() >= 0) & (movableObject.getY() >= 0) &
//                (movableObject.getX() < nSize) & (movableObject.getY() < nSize)) ? true : false;
//    }

//    //Проверяем, находится ли по координатам объекта movable куча золота
//    public static boolean isObjectInGold(Movable movable) {
//        return playingFieldCells[movable.getY()][movable.getX()].getClass().isInstance(PieceOfGold.class);
//    }
//
//    //Проверяем, находится ли по координатам объекта movable дыра
//    public static boolean isObjectInHole(Movable movable) {
//        return playingFieldCells[movable.getY()][movable.getX()].getClass().isInstance(Hole.class);
//    }
//    //Проверяем, находится ли по координатам объекта игрок
//    public static boolean isPlayerInCell(Movable movable) {
//        return playingFieldCells[movable.getY()][movable.getX()].getClass().isInstance(Player.class);
//    }
//

//    public static boolean canRobotMove(Movable movable){
//       return playingFieldCells[movable.getY()][movable.getX()].robotCanMove;

}

