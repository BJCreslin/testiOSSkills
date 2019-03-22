package ru.bjcreslin.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bjcreslin.service.RobotFactory;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;

import static org.junit.Assert.*;

public class MoveDispatcherTest {
    MoveDispatcher moveDispatcher;
    Robot robot;
    Integer x;
    Integer y;
    int ytemp;
    int xtemp;

    @Before
    public void setUp() throws Exception {
        PaintScreen paintScreen = new paintsScreenMatrix();
        Game game = new Game(paintScreen, 20, 5, 4, 1);
        moveDispatcher = new MoveDispatcher();

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