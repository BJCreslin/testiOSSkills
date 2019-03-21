package ru.bjcreslin.controller;

import ru.bjcreslin.model.Hole;
import ru.bjcreslin.model.Movable;

import java.util.Random;

public class HoleFactory extends GameObjectFactory {
    @Override
    public Movable getNewGameObject() {
        int x = new Random().nextInt(getGame().getNSize());
        int y = new Random().nextInt(getGame().getNSize());
        return new Hole();
    }
}
