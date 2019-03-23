package ru.bjcreslin.service;

import lombok.Getter;
import ru.bjcreslin.model.GameParametres;

public class GameService {
    @Getter
    GameParametres gameParametres;

    public GameService() {
        gameParametres = new GameParametres();
        gameParametres.setNSize(20);
        gameParametres.setNPieceOfGold(5);
        gameParametres.setNHole(12);
        gameParametres.setNRobots(25);
        gameParametres.setRepeatGame(true);
    }

    public GameParametres startMenu() {
        boolean menuExit = false;
        gameParametres.setRepeatGame(true);
        while (!menuExit) {
            ConsoleService.printMenu();

            switch (ConsoleService.inputOneNumber()) {
                case 1:
                    menuExit = true;
                    break;
                case 2:
                    options();
                    break;
                case 3:
                    gameParametres.setRepeatGame(false);
                    menuExit = true;
                default:
            }
        }
        return gameParametres;
    }

    private void options() {
        ConsoleService.println("Введите размер игрового поля, текущий=" + gameParametres.getNSize());
        gameParametres.setNSize(ConsoleService.inputOneNumber());
        ConsoleService.println("Введите количество золота, текущий=" + gameParametres.getNPieceOfGold());
        gameParametres.setNPieceOfGold(ConsoleService.inputOneNumber());
        ConsoleService.println("Введите количество дырок, текущий=" + gameParametres.getNHole());
        gameParametres.setNHole(ConsoleService.inputOneNumber());
        ConsoleService.println("Введите количество роботов, текущий=" + gameParametres.getNRobots());
        gameParametres.setNRobots(ConsoleService.inputOneNumber());
    }

}
