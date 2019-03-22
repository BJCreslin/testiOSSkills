package ru.bjcreslin.service;

import ru.bjcreslin.model.Border;
import ru.bjcreslin.model.Movable;
import ru.bjcreslin.model.StaticAble;

import java.util.Deque;

public class ScreeenFieldMaker {

    /**
     * Делает матрицу игровых объектов
     * @param staticAblesField  матрица статичных объектов
     * @param movableQueue -набор динамичных объектов
     * @return матрица символов игровых объектов
     */
    public static String[][] gameSymbolsScreeenFieldMaker(StaticAble[][] staticAblesField, Deque<Movable> movableQueue) {
        /*
        Размер больше исходной матрицы. Это надо добавления границы.
         */
        String[][] gameObjects = new String[staticAblesField.length + 2][staticAblesField.length + 2];
         /*
    Формирует матрицу символов из матрицы статичных объектов
    +добавляем рамку
     */
        for (int i = 0; i < staticAblesField.length; i++) {
            for (int j = 0; j < staticAblesField.length; j++) {
                gameObjects[i + 1][j + 1] = staticAblesField[i][j].getObjectSymbol();
            }
        }
        /*
        Добавляем динамические объекты
         */

        movableQueue.forEach(movable -> gameObjects[movable.getY() + 1][movable.getX() + 1] = movable.getObjectSymbol());

        /*
        Добавляем границу поля
         */
        for (int i = 0; i < gameObjects.length; i++) {
            gameObjects[i][0] = Border.getInstance().getObjectSymbol();
            gameObjects[0][i] = Border.getInstance().getObjectSymbol();
            gameObjects[i][gameObjects.length - 1] = Border.getInstance().getObjectSymbol();
            gameObjects[gameObjects.length - 1][i] = Border.getInstance().getObjectSymbol();
        }
        return gameObjects;
    }
}
