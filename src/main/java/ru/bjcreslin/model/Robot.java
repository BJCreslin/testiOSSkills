package ru.bjcreslin.model;

import lombok.Data;

import java.util.Random;

/**
 * Класс робот
 */


@Data
public class Robot extends Movable implements GameObject {
    //Количество шагов парализации. Будет уменьшаться с каждым шагом, пока не достигнет 0.
    //Если равно 0, то робот может двигаться по игровому полю
    // При парализации становится равным 5
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

            /* Если робот наезжает на игрока, то конец игры*/
            if (PlayingField.isPlayerInField(this)) {
                Game.setPlayerAlive(false);
            } else {

            /* Если робот выезжает за пределы экрана или на кучу золота или дыру, то
             возвращаем старые координаты*/
                if ((!PlayingField.isObjectInField(this)) |
                        (!PlayingField.isObjectInGold(this)) |
                        (!PlayingField.isObjectInHole(this))) {
                    restoreCoordinate();
                } else {
                    PlayingField.setGround(this.getXtemp(), this.getYtemp());

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

    private int getXtemp() {
        return xtemp;
    }

    private int getYtemp() {
        return ytemp;
    }

    private int xtemp;
    private int ytemp;


}
