package ru.bjcreslin.service;

import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.GameObject;
import ru.bjcreslin.model.Robot;

import java.util.Random;

public class RobotFactory extends GameObjectFactory {

    public RobotFactory(Game game) {
        super(game);
    }

    @Override
    public GameObject getNewGameObject() {
        int x = new Random().nextInt(getGame().getNSize());
        int y = new Random().nextInt(getGame().getNSize());
        return new Robot(x, y);
    }
}
