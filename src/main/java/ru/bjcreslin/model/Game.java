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

    List<PieceOfGold> pieceOfGoldList; //коллекция

    public Game(int nSize, int nPieceOfGold, int nHole, int nRobots) {
        this.nSize = nSize;
        this.nPieceOfGold = nPieceOfGold;
        this.nHole = nHole;
        this.nRobots = nRobots;

        PlayingField playingField = new PlayingField(nSize);

        Player player = new Player(nSize);

      //  List<PieceOfGold> pieceOfGoldList = PieceOfGoldController.getNewPieceOfGoldList(nSize, nPieceOfGold, player);

        //Запускаем игру
        playerAlive = true;

    }
}
