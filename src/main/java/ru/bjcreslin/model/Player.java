package ru.bjcreslin.model;

/**
 * Игровой объект - player
 */
public class Player extends Movable implements GameObject {


    //количество зарядов шокера
    private int numberShockerCharges;


    @Override
    /**
     * todo сделать
     */
    public void action() {

    }

    public Player(int nSize) {
        //Игрок всегда начинает с центра комнаты.
        super((int) (nSize / 2.), (int) (nSize / 2.));

        //    Игрок при старте игры имеет 3 заряда излучателя
        this.numberShockerCharges = 3;
    }
}
