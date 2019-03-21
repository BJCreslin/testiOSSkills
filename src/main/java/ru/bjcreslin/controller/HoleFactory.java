package ru.bjcreslin.controller;

import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.GameObject;
import ru.bjcreslin.model.Hole;

import java.util.Random;

/**
 * Фабрика дырок в комнате
 */
public class HoleFactory extends GameObjectFactory {

    public HoleFactory(Game game) {
        super(game);
    }

    @Override
    public GameObject getNewGameObject() {
        int x = new Random().nextInt(getGame().getNSize());
        int y = new Random().nextInt(getGame().getNSize());
        return new Hole();
    }
}
