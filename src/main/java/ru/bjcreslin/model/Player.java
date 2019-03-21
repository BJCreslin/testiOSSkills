package ru.bjcreslin.model;

/**
 * Игровой объект - player
 */
public class Player extends Movable implements GameObject {

    /*
       Изображение игрока - +;
        */
    private String symbolForview = "+";

    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }
    //количество зарядов шокера
    private int numberShockerCharges;


    @Override
    /**
     * todo сделать реализацию
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
