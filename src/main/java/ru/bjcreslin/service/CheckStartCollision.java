package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import ru.bjcreslin.model.*;

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
        StaticAble[][] staticAbles = game.getPlayingField().getPlayingFieldCells().clone();
        Movable tempMovable = new Robot(movable.getX(), movable.getY());

        for (Map.Entry<Integer, BiConsumer<Integer, Integer>> entry : moveDispatcher.getConsumerMap().entrySet()) {
            entry.getValue().accept(tempMovable.getX(), tempMovable.getY());
            if ((staticAbles[tempMovable.getX()][tempMovable.getY()].isRobotCanMove())) {
                return true;
            }
        }
        return false;
    }

    /*
    Проверяем есть ли вокруг игрока статичесие ячейки, по которым игрок может ходить.
    Если есть хоть одна, то возвращает true
     */
    public boolean isPlayerCanMoveStatic() {
        MoveDispatcher moveDispatcher = new MoveDispatcher();
        StaticAble[][] staticAbles = game.getPlayingField().getPlayingFieldCells().clone();
        Player tempPlayer = new Player(game.getPlayer().getX(), game.getPlayer().getY());

        for (Map.Entry<Integer, BiConsumer<Integer, Integer>> entry : moveDispatcher.getConsumerMap().entrySet()) {
            entry.getValue().accept(tempPlayer.getX(), tempPlayer.getY());
            if ((staticAbles[tempPlayer.getX()][tempPlayer.getY()].isPlayerCanMove()) &
                    (!staticAbles[tempPlayer.getX()][tempPlayer.getY()].isDeathAble())) {
                return true;
            }
        }
        return false;

    }

    /*
    ПРоверка, что объект не вышел за границы игрового поля.
     */
    public boolean isMovedInField(Movable movable) {
        return (movable.getX() >= 0 & movable.getY() >= 0 & movable.getX() < game.getNSize() & movable.getY() < game.getNSize()) ? true : false;
    }

    private int distance(Movable movable1, Movable movable2) {
        return (Math.abs(movable1.getX() - movable2.getX()) + Math.abs(movable1.getY() - movable2.getY()));
    }

}
