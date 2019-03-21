package ru.bjcreslin.controller;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.Movable;

@AllArgsConstructor
public class CheckMovableCollision {
    @Setter
    Game game;


    public boolean isPlayerInField(Movable movable) {
        return ((movable.getX() == game.getPlayer().getX()) & (movable.getY() == game.getPlayer().getY())) ? true : false;
    }

}
