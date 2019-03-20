package ru.bjcreslin.model;


import lombok.Data;

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

    private int score;//счёт игры
    private Player player;


    public Game(int nSize, int nPieceOfGold, int nHole, int nRobots) {
        this.nSize = nSize;
        this.nPieceOfGold = nPieceOfGold;
        this.nHole = nHole;
        this.nRobots = nRobots;

        PlayingField playingField = new PlayingField(nSize);
        playingField.fillAllCellsGround();

        this.player = new Player(nSize);

        //Делаем игрока живым
        playerAlive = true;
    }

    public boolean isPlayerHere(Movable movable) {
        return ((movable.getX() == player.getX()) & (movable.getY() == player.getY())) ? true : false;

    }

}
