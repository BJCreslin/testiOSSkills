package ru.bjcreslin.controller;

import lombok.AllArgsConstructor;
import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.Movable;
import ru.bjcreslin.model.MoveDispatcher;

import java.util.Map;
import java.util.function.BiConsumer;

@AllArgsConstructor
public class CheckStartCollision {
    private Game game;

    /*проверка условия : на стартовой позиции между игроком и ближайшими к нему роботами должно быть минимум 2 клетки.
     * */
    public boolean isPlayerFar(Movable movable) {
        return (distance(movable, game.getPlayer()) > 2) ? true : false;
    }

    /**
     * todo сделать реализацию
     *
     * @param movable
     * @return
     */
    public boolean isNotEnvironmentByHole(Movable movable) {
        MoveDispatcher moveDispatcher = new MoveDispatcher();

        for (Map.Entry<Integer, BiConsumer<Integer, Integer>> entry : moveDispatcher.getConsumerMap().entrySet()) {
            movable.saveCoord();
            entry.getValue().accept(movable.getX(), movable.getY());
            movable.restroreCoord();
        }

        return false;
    }


    private int distance(Movable movable1, Movable movable2) {
        return (Math.abs(movable1.getX() - movable2.getX()) + Math.abs(movable1.getY() - movable2.getY()));
    }

}
