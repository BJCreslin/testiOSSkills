package ru.bjcreslin.model;

/**
 * Игровой объект - player
 */
public class Player implements GameObject {

    //координаты игрока
    private int x;
    private int y;

    //количество зарядов шокера
    private int numberShockerCharges;


    public Player(int nSize) {
        //Игрок всегда начинает с центра комнаты.
        this.x = (int) (nSize / 2.);
        this.y = (int) (nSize / 2.);

        //    Игрок при старте игры имеет 3 заряда излучателя
        this.numberShockerCharges = 3;
    }
}
