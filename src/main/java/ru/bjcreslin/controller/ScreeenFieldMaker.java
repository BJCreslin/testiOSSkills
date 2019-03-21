package ru.bjcreslin.controller;

import ru.bjcreslin.model.Movable;
import ru.bjcreslin.model.StaticAble;

import java.util.Queue;

public class ScreeenFieldMaker {

    /*
    Формирует матрицу символов из матрицы статичных объектов
     */
    public static String[][] gameSymbolsScreeenFieldMaker(StaticAble[][] staticAblesField, Queue<Movable> movableQueue) {
        String[][] gameObjects = new String[staticAblesField.length][staticAblesField.length];
        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects.length; j++) {
                gameObjects[i][j] = staticAblesField[i][j].getObjectSymbol();
            }
        }
        if (!movableQueue.isEmpty()) {
            movableQueue.stream().forEach(x -> gameObjects[x.getY()][x.getY()] = x.getObjectSymbol());
        }

        return gameObjects;
    }
}
