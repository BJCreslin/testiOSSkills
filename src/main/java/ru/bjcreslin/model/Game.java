package ru.bjcreslin.model;


import lombok.Data;

import java.util.List;

@Data
public class Game {
    private boolean playerAlive;  // переменная игры. true- означает, что игра идёт . false- игре конец
    private int nSize; // Размер игрового поля ( N*N клеток)
    private int nPieceOfGold;// количество кусков золота
    private int nHole;//Количество дырок в полу
    private int nRobots;//количество роботов



    public Game(int nSize, int nPieceOfGold, int nHole, int nRobots) {
        this.nSize = nSize;
        this.nPieceOfGold = nPieceOfGold;
        this.nHole = nHole;
        this.nRobots = nRobots;

        PlayingField playingField = new PlayingField(nSize);
        playingField.fillAllCellsGround();

        Player player = new Player(nSize);

        //Делаем игрока живым
        playerAlive = true;
    }

}
