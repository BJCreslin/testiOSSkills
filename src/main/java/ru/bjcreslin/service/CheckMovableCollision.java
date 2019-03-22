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
     * @param movable робот или иной двигающийся объект
     * @return нахождение игрока и movable в одной клетке
     */
    public boolean isPlayerInCell(Movable movable) {
        return ((movable.getX().equals(game.getPlayer().getX())) &
                (movable.getY().equals(game.getPlayer().getY())));
    }


    /**
     * ПРоверка, что объект не вышел за границы игрового поля.
     *
     * @param movable обот или иной двигающийся объект
     * @return Значение true - объект в игровом поле
     */

    public boolean isMovedInField(Movable movable) {
        return (movable.getX() >= 0 & movable.getY() >= 0 & movable.getX() < game.getNSize() & movable.getY() < game.getNSize());
    }
}
