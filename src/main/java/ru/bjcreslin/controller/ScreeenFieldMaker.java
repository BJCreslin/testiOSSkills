package ru.bjcreslin.controller;

import ru.bjcreslin.model.Movable;
import ru.bjcreslin.model.StaticAble;

import java.util.Deque;

public class ScreeenFieldMaker {


    public static String[][] gameSymbolsScreeenFieldMaker(StaticAble[][] staticAblesField, Deque<Movable> movableQueue) {
        String[][] gameObjects = new String[staticAblesField.length][staticAblesField.length];
         /*
    Формирует матрицу символов из матрицы статичных объектов
     */
        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects.length; j++) {
                gameObjects[i][j] = staticAblesField[i][j].getObjectSymbol();
            }
        }
        /*
        Добавляем динамические объекты
         */
         {
            movableQueue.stream().forEach(movable -> gameObjects[movable.getY()][movable.getX()] = movable.getObjectSymbol());
        }

        return gameObjects;
    }
}
