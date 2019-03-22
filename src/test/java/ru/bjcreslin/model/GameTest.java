package ru.bjcreslin.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;

import static org.junit.Assert.*;

public class GameTest {
    PaintScreen paintScreen;

    @Before
    public void name() {
        paintScreen = new paintsScreenMatrix();
    }

    @Test
    public void gameTestNRobots() {
        int nRobots = 3;
        Game game = new Game(paintScreen, 20, 5, 4, nRobots);
        Assert.assertEquals(game.getRobotList().size(), nRobots);
    }

    @Test
    public void gameTestNSize() {
        int nSize = 20;
        Game game = new Game(paintScreen, nSize, 5, 4, 4);
        Assert.assertEquals(game.playingField.getPlayingFieldCells().length, nSize);
    }


}