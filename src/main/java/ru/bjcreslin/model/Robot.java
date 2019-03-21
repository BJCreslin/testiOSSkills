package ru.bjcreslin.model;

import lombok.Getter;

import java.util.Random;

/**
 * Класс робот
 */


public class Robot extends Movable implements GameObject {
    /*
     Изображение робота - +;
      */
    private String symbolForview = "X";
    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }

    //Количество шагов парализации. Будет уменьшаться с каждым шагом, пока не достигнет 0.
    //Если равно 0, то робот может двигаться по игровому полю
    // При парализации становится равным 5
    @Getter
    private int numberOfStepsParalyze;

    //Новый робот не парализован, и создается в координатах
    public Robot(int x, int y) {
        super(x, y);
        this.numberOfStepsParalyze = 0;
    }

    /*
    Хождние робота - случайное.
     */
    private void moveRandom() {
        getMoveDispatcher().getConsumerMap().get(new Random().nextInt(3));
    }


    @Override
    public void action() {
        //Если робот парализован, то он пропускает ход, и количество шагов до оживания уменьшается.
        if (numberOfStepsParalyze > 0) {
            numberOfStepsParalyze--;
        }
        //Иначе робот делает ход
        else {
            //сохраняем предыдущие координаты хода для возможной отмены
            saveCoordinate();

            moveRandom();
/*
todo переделать
 */
            /* Если робот наезжает на игрока, то конец игры*/
            if (PlayingField.isPlayerInField(this)) {
                Game.setPlayerAlive(false);
            } else {


                if (PlayingField.canRobotMove(this) & PlayingField.isObjectInField(this)) {
                    /* Заполняем старое расположение робота землей*/
                    PlayingField.setGround(this.xtemp, this.ytemp);
                } else {
                 /* Если робот выезжает за пределы экрана или на кучу золота или дыру, то
             возвращаем старые координаты*/
                    restoreCoordinate();
                }
            }
        }
    }

    private void saveCoordinate() {
        xtemp = this.getX();
        ytemp = this.getY();
    }

    private void restoreCoordinate() {
        setX(xtemp);
        setY(ytemp);
    }


    private int xtemp;
    private int ytemp;


}
