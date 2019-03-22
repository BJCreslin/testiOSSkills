package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.Movable;

@AllArgsConstructor
public class CheckMovableCollision {
    private Game game;

    /**
     * Проверяет ,есть ли на месте Movable игрок
     *
     * @param movable
     * @return нахождение игрока и movable в одной клетке
     */
    public boolean isPlayerInField(Movable movable) {
        return ((movable.getX() == game.getPlayer().getX()) & (movable.getY() == game.getPlayer().getY()));
    }


    /**
     * ПРоверка, что объект не вышел за границы игрового поля.
     *
     * @param movable
     * @return Значение - объект в поле?
     */

    public boolean isMovedInField(Movable movable) {
        return (movable.getX() >= 0 & movable.getY() >= 0 & movable.getX() < game.getNSize() & movable.getY() < game.getNSize());
    }


}
