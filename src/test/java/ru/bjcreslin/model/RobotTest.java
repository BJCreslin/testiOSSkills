package ru.bjcreslin.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bjcreslin.service.RobotFactory;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;


public class RobotTest {
    RobotFactory robotFactory;
    Game game;

    @Before
    public void beforeTest() {
        PaintScreen paintScreen = new paintsScreenMatrix();
        GameParametres gameParametres = new GameParametres(20, 5, 4, 1);
        game = new Game(paintScreen, gameParametres);
        robotFactory = new RobotFactory(game);
    }

    @Test
    public void action() {

        Robot robot = (Robot) robotFactory.getNewGameObject();
        int x = robot.getX();
        int y = robot.getY();
        //robot.action();
        robot.moveRandom();
        Assert.assertFalse((robot.getX() == x) & (robot.getY() == y));


    }
}