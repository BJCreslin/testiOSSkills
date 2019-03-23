package ru.bjcreslin.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bjcreslin.service.RobotFactory;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;


public class MoveDispatcherTest {
    private MoveDispatcher moveDispatcher;
    private Robot robot;
    private Integer x;
    private Integer y;
    private int ytemp;
    private int xtemp;

    @Before
    public void setUp() {
        PaintScreen paintScreen = new paintsScreenMatrix();
        GameParametres gameParametres = new GameParametres(20, 5, 4, 2);
        Game game = new Game(paintScreen, gameParametres);
        moveDispatcher =  MoveDispatcher.getInstance();

        RobotFactory robotFactory = new RobotFactory(game);
        robot = (Robot) robotFactory.getNewGameObject();

        ytemp = robot.getY().intValue();
        xtemp = robot.getX().intValue();
    }

    @Test
    public void up() {
        moveDispatcher.up(robot);
        Assert.assertEquals(robot.getY().intValue(), ++ytemp);
        Assert.assertEquals(robot.getX().intValue(), xtemp);

        ytemp = robot.getY().intValue();
        xtemp = robot.getX().intValue();

        moveDispatcher.getConsumerMap().get(0).accept(robot);

        Assert.assertEquals(robot.getY().intValue(), ++ytemp);
        Assert.assertEquals(robot.getX().intValue(), xtemp);
    }

    @Test
    public void down() {
        moveDispatcher.down(robot);
        Assert.assertEquals(robot.getY().intValue(), --ytemp);
        Assert.assertEquals(robot.getX().intValue(), xtemp);

        ytemp = robot.getY().intValue();
        xtemp = robot.getX().intValue();

        moveDispatcher.getConsumerMap().get(1).accept(robot);
        Assert.assertEquals(robot.getY().intValue(), --ytemp);
        Assert.assertEquals(robot.getX().intValue(), xtemp);
    }

    @Test
    public void right() {
        moveDispatcher.right(robot);
        Assert.assertEquals(robot.getY().intValue(), ytemp);
        Assert.assertEquals(robot.getX().intValue(), ++xtemp);

        ytemp = robot.getY().intValue();
        xtemp = robot.getX().intValue();

        moveDispatcher.getConsumerMap().get(2).accept(robot);
        Assert.assertEquals(robot.getY().intValue(), ytemp);
        Assert.assertEquals(robot.getX().intValue(), ++xtemp);
    }

    @Test
    public void left() {
        moveDispatcher.left(robot);
        Assert.assertEquals(robot.getY().intValue(), ytemp);
        Assert.assertEquals(robot.getX().intValue(), --xtemp);

        ytemp = robot.getY().intValue();
        xtemp = robot.getX().intValue();

        moveDispatcher.getConsumerMap().get(3).accept(robot);
        Assert.assertEquals(robot.getY().intValue(), ytemp);
        Assert.assertEquals(robot.getX().intValue(), --xtemp);
    }
}