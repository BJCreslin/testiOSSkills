package ru.bjcreslin;

import ru.bjcreslin.model.Game;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;

public class Application {

    public static void main(String[] args) {
        //(int nSize, int nPieceOfGold, int nHole, int nRobots)
        PaintScreen paintScreen = new paintsScreenMatrix();
        Game game = new Game(paintScreen, 10, 5, 4, 3);
        game.play();


    }

}
