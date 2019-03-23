package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import ru.bjcreslin.model.*;

import java.util.Map;
import java.util.function.Consumer;

@AllArgsConstructor
public class CheckStartCollision {
    private Game game;

    /**
     * проверка условия : на стартовой позиции между игроком и ближайшими к нему роботами должно быть минимум 2 клетки.
     *
     * @param movable робот или другой объект
     * @return больше ли чем две клетки до игрока
     */

    public boolean isPlayerFar(Movable movable) {
        return (distance(movable, game.getPlayer()) > 3);
    }

    /**
     * Проверяет на неокружение дырками.
     * В проверяемом месте создаем робота и пытаемся пройти по всем возможным для него ходам.
     * Если хоть по одному можно пройти, то результат - true
     *
     * @param movable робот
     * @return возможность пройти
     */
    public boolean isNotEnvironmentByHole(Movable movable) {
        MoveDispatcher moveDispatcher = movable.getMoveDispatcher();
        Movable tempMovable = new Robot(game, movable.getX(), movable.getY());

        for (Map.Entry<Integer, Consumer<Movable>> entry : moveDispatcher.getConsumerMap().entrySet()) {
            entry.getValue().accept(movable);
            if ((game.getPlayingField().getCell(tempMovable.getX(), tempMovable.getY()).
                    isRobotCanMove())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяем есть ли вокруг игрока статичесие ячейки, по которым игрок может безопасно ходить.
     * Если есть хоть одна, то возвращает true
     *
     * @return возможность сделать ход
     */
    public boolean isPlayerCanMoveStatic() {

        StaticAble[][] staticAbles = game.getPlayingField().getPlayingFieldCells().clone();
        Player tempPlayer = new Player(game.getPlayer().getX(), game.getPlayer().getY());
        MoveDispatcher moveDispatcher = tempPlayer.getMoveDispatcher();

        for (Map.Entry<Integer, Consumer<Movable>> entry : moveDispatcher.getConsumerMap().entrySet()) {
            entry.getValue().accept(tempPlayer);
            if ((staticAbles[tempPlayer.getX()][tempPlayer.getY()].isPlayerCanMove()) &
                    (!staticAbles[tempPlayer.getX()][tempPlayer.getY()].isDeathAble())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Измеряет расстояние между двумя Movable объектами
     *
     * @param movable1 робот или игрок
     * @param movable2 робот или игрок
     * @return расстояние в ходах
     */
    private int distance(Movable movable1, Movable movable2) {
        return (int) Math.sqrt((movable1.getX() - movable2.getX()) * (movable1.getX() - movable2.getX()) +
                (movable1.getY() - movable2.getY()) * (movable1.getY() - movable2.getY()));
    }

}
