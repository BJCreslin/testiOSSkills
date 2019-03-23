package ru.bjcreslin;

import ru.bjcreslin.model.Game;
import ru.bjcreslin.model.GameParametres;
import ru.bjcreslin.service.GameService;
import ru.bjcreslin.view.PaintScreen;
import ru.bjcreslin.view.paintsScreenMatrix;

public class Application {

    public static void main(String[] args) {
        //(int nSize, int nPieceOfGold, int nHole, int nRobots)
        PaintScreen paintScreen = new paintsScreenMatrix();
        GameService gameService = new GameService();
        GameParametres gameParametres;
        while ((gameService.getGameParametres()).isRepeatGame()) {
            gameParametres = gameService.startMenu();
            Game game = new Game(paintScreen, gameParametres);
            game.play();
        }

    }


}
