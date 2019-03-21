package ru.bjcreslin.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.GameObject;

@AllArgsConstructor
public abstract class GameObjectFactory {
    @Getter
    private Game game;

    public abstract GameObject getNewGameObject();

}
