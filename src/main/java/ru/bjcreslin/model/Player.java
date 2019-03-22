package ru.bjcreslin.model;

/**
 * Игровой объект - player
 */
public class Player extends Movable implements GameObject {

    /*
       Изображение игрока - +;
        */
    private String symbolForview;

    {
        symbolForview = "1";
    }

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


     Player(int nSize) {
        //Игрок всегда начинает с центра комнаты.
        super((int) (nSize / 2.), (int) (nSize / 2.));

        //    Игрок при старте игры имеет 3 заряда излучателя
        this.numberShockerCharges = 3;
    }

    //Конструктор для виртуальных ходов, при проверках.
    public Player(int x, int y) {
        super(x, y);
    }

     boolean isHere(int x, int y) {
        return (x == this.getX()) & (y == this.getY());
    }
}
