package ru.bjcreslin.model;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;


 class MoveDispatcher {
    @Getter
    private Map<Integer, BiConsumer<Integer, Integer>> consumerMap;

     MoveDispatcher() {
        consumerMap = new HashMap<>();
        consumerMap.put(0, this::up);
        consumerMap.put(1, this::down);
        consumerMap.put(2, this::right);
        consumerMap.put(3, this::left);
    }

    //Увеличиваем координату y - движение вверх
    void up(Integer x, Integer y) {
        y++;
    }

    //Уменьшаем координату y - движение вниз
    void down(Integer x, Integer y) {
        y--;
    }

    //Увеличиваем координату x - движение вправо
    void right(Integer x, Integer y) {
        x++;
    }

    //Увеличиваем координату x - движение вправо
    void left(Integer x, Integer y) {
        x--;
    }

}
