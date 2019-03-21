package ru.bjcreslin.model;


import lombok.Data;
import ru.bjcreslin.controller.CheckMovableCollision;
import ru.bjcreslin.controller.ScreeenFieldMaker;
import ru.bjcreslin.view.PaintScreen;

import java.util.List;

@Data
public class Game {
    public static void setPlayerAlive(boolean playerAlive) {
        Game.playerAlive = playerAlive;
    }

    private static boolean playerAlive;  // переменная игры. true- означает, что игра идёт . false- игре конец
    private int nSize; // Размер игрового поля ( N*N клеток)
    private int nPieceOfGold;// количество кусков золота
    private int nHole;//Количество дырок в полу
    private int nRobots;//количество роботов


    PaintScreen paintScreen; // вывод на экран
    PlayingField playingField; // Поле статичных объектов
    CheckMovableCollision checkMovableCollision;// Контроллер колиизий движения объектов


    private int score;//счёт игры
    private Player player;
    private List<Robot> robotList;


    public Game(PaintScreen paintScreen, int nSize, int nPieceOfGold, int nHole, int nRobots) {
        this.nSize = nSize;
        this.nPieceOfGold = nPieceOfGold;
        this.nHole = nHole;
        this.nRobots = nRobots;

        this.paintScreen = paintScreen;

        PlayingField playingField = new PlayingField(nSize);
        playingField.fillAllCellsGround();

        this.player = new Player(nSize);


        //Делаем игрока живым
        playerAlive = true;
    }

    public void play() {

        paintScreen.viewMatrix(ScreeenFieldMaker.gameSymbolsScreeenFieldMaker(playingField.getPlayingFieldCells(),null));

    }

    public boolean isPlayerHere(Movable movable) {
        return ((movable.getX() == player.getX()) & (movable.getY() == player.getY())) ? true : false;
    }

}
