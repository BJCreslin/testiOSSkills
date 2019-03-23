package ru.bjcreslin.model;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Диспетчер движения для двигающихся объектов
 */
public class MoveDispatcher {
    @Getter
    private Map<Integer, Consumer<Movable>> consumerMap;
    private static MoveDispatcher instance;

    private MoveDispatcher() {
        consumerMap = new HashMap<>();
        consumerMap.put(0, this::up);
        consumerMap.put(1, this::down);
        consumerMap.put(2, this::right);
        consumerMap.put(3, this::left);
    }

    static MoveDispatcher getInstance() {
        if (instance == null) {
            instance = new MoveDispatcher();
        }
        return instance;
    }

    //Увеличиваем координату y - движение вверх
    void up(Movable movable) {
        movable.setY(movable.getY() - 1);
    }

    //Уменьшаем координату y - движение вниз
    void down(Movable movable) {
        movable.setY(movable.getY() + 1);
    }

    //Увеличиваем координату x - движение вправо
    void right(Movable movable) {
        movable.setX(movable.getX() + 1);
    }

    //Увеличиваем координату x - движение вправо
    void left(Movable movable) {
        movable.setX(movable.getX() - 1);
    }

}
