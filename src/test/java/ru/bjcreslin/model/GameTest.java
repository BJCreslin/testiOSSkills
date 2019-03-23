package ru.bjcreslin.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;


public class GameTest {
    private PaintScreen paintScreen;
    Game game;

    @Before
    public void name() {
        paintScreen = new paintsScreenMatrix();

    }

    @Test
    public void gameTestNRobots() {
        int nRobots = 3;
        GameParametres gameParametres = new GameParametres(20, 5, 4, nRobots);
        game = new Game(paintScreen, gameParametres);
        Assert.assertEquals(game.getRobotList().size(), nRobots);
    }

    @Test
    public void gameTestNSize() {
        int nSize = 20;
        GameParametres gameParametres = new GameParametres(nSize, 5, 4, 4);
        game = new Game(paintScreen, gameParametres);
        Assert.assertEquals(game.playingField.getPlayingFieldCells().length, nSize);
    }

    @Test
    public void gameTestNGold() {
        int nGold = 7;
        GameParametres gameParametres = new GameParametres(20, nGold, 4, 5);
        game = new Game(paintScreen, gameParametres);
        Assert.assertEquals(game.getNPieceOfGold(), nGold);
    }


}