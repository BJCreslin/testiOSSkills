package ru.bjcreslin.controller;

import lombok.AllArgsConstructor;
import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.Movable;

@AllArgsConstructor
public class CheckMovableCollision {
    private Game game;


    public boolean isPlayerInField(Movable movable) {
        return ((movable.getX() == game.getPlayer().getX()) & (movable.getY() == game.getPlayer().getY())) ? true : false;
    }




}
