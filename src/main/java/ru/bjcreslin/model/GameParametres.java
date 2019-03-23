package ru.bjcreslin.model;

import lombok.Data;

/**
 * Класс параметров игры
 */
@Data
public class GameParametres {
    private Integer nSize;
    private Integer nPieceOfGold;
    private Integer nHole;
    private Integer nRobots;
    private boolean repeatGame;


    public GameParametres(Integer nSize, Integer nPieceOfGold, Integer nHole, Integer nRobots) {
        this.nSize = nSize;
        this.nPieceOfGold = nPieceOfGold;
        this.nHole = nHole;
        this.nRobots = nRobots;
    }
}
