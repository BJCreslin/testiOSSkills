package ru.bjcreslin.model;

import ru.bjcreslin.service.ConsoleService;

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
        ConsoleService.println("Ход игрока (8-вверх, 2-вниз, 4-влево, 6-вправо, 5- разряд шокера, 0- сдаюсь)");
        int stepPlayer;

        do {

            stepPlayer = ConsoleService.inputOneNumber();
        } while (!((stepPlayer == 0) | (stepPlayer == 8)));
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
