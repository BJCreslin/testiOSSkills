package ru.bjcreslin.model;

import lombok.Getter;
import ru.bjcreslin.service.ConsoleService;


/**
 * Игровой объект - player
 */
public class Player extends Movable implements GameObject {
    private Game game;

    /*
       Изображение игрока - ;
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
    @Getter
    private int numberShockerCharges;
    /**
     * Хранение предыдущего хода
     */
    private int xTemp;
    private int yTemp;

    /**
     * Конструктор для создания Игрока
     *
     * @param game данные игры. Игрок как бы видит всё поле
     */
    Player(Game game) {
        //Игрок всегда начинает с центра комнаты.
        super((int) (game.getNSize() / 2.), (int) (game.getNSize() / 2.));

        //    Игрок при старте игры имеет 3 заряда излучателя
        this.numberShockerCharges = 3;
        this.game = game;
    }


    /**
     * Конструктор для виртуальных ходов, при проверках.
     */
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    /** метод хоздения игрока
     * игрок двигается в соответсвии с введенными данными
     */
    public void action() {
        ConsoleService.println("Ход игрока (8-вверх, 2-вниз, 4-влево, 6-вправо, 5- разряд шокера, 0- сдаюсь)");
        int stepPlayer;
        MoveDispatcher moveDispatcher = MoveDispatcher.getInstance();
        do {
            stepPlayer = ConsoleService.inputOneNumber();
        } while (!((stepPlayer == 0) | (stepPlayer == 8) | (stepPlayer == 4) |
                (stepPlayer == 2) | (stepPlayer == 6) | (stepPlayer == 5)));

        saveCoord();

        switch (stepPlayer) {
            case 0:
                game.setPlayerAlive(false);
                break;
            case 8:
                moveDispatcher.up(this);
                break;
            case 2:
                moveDispatcher.down(this);
                break;
            case 4:
                moveDispatcher.left(this);
                break;
            case 6:
                moveDispatcher.right(this);
                break;
            case 5:
                fire();
        }
        if (game.checkMovableCollision.isMovedInField(this)) {
            if (game.playingField.getCell(getX(), getY()).isDeathAble()) {
                game.setPlayerAlive(false);
            } else if (game.playingField.getCell(getX(), getY()).reachAble) {
                game.setNPieceOfGold(game.getNPieceOfGold() - 1);
                game.playingField.setGround(getX(), getY());
            }

        } else {
            restoreCoord();
        }
    }

    /**
     * Применение шокера
     */
    private void fire() {
        if (numberShockerCharges > 0) {
            numberShockerCharges--;
            for (Robot robot : game.getRobotList()) {
                if ((Math.abs(robot.getX() - getX()) < 2) & (Math.abs(robot.getY() - getY()) < 2)) {
                    robot.setParalyze();
                }
            }
        }

    }

    /**
     * В этой ли точке игрок
     */
    boolean isHere(int x, int y) {
        return (x == this.getX()) & (y == this.getY());
    }

    void saveCoord() {
        xTemp = getX();
        yTemp = getY();
    }

    void restoreCoord() {
        setX(xTemp);
        setY(yTemp);
    }
}
