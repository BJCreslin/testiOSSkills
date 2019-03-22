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
    private String symbolForview = "ᛯ";

    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }

    //Количество шагов парализации. Будет уменьшаться с каждым шагом, пока не достигнет 0.
    //Если равно 0, то робот может двигаться по игровому полю
    // При парализации становится равным 5
    @Getter
    private int numberOfStepsParalyze;
    private Game game;

    //Новый робот не парализован, и создается в координатах
    public Robot(Game game, Integer x, Integer y) {
        super(x, y);
        this.numberOfStepsParalyze = 0;
        this.game = game;
    }

    /*
    Хождние робота - случайное.
     */
    public void moveRandom() {
        getMoveDispatcher().getConsumerMap().get(new Random().nextInt(4)).accept(this);
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
            saveCoord();

            /*пытаемся сделать ход, что бы робот не выходил за игровое поле и
             и на новое поле робот может заезжать*/
            do {
                restoreCoord();
                moveRandom();
                //todo тут неаботает . не выходит из цикла
                if (!(game.checkMovableCollision.isMovedInField(this))) {
                    continue;
                }

                if (game.getPlayingField().getCell(this.getX(), this.getY()).isRobotCanMove()) {
                    break;
                }
            }
            while (true);

            //Если робот догоняет игрока, то убивает его.
            if (game.checkMovableCollision.isPlayerInCell(this)) {
                game.slayPlayer();
            }
        }

    }


///*
//todo переделать
// */
//            /* Если робот наезжает на игрока, то конец игры*/
//            if (PlayingField.isPlayerInCell(this)) {
//                Game.setPlayerAlive(false);
//            } else {
//
//
//                if (PlayingField.canRobotMove(this) & PlayingField.isObjectInField(this)) {
//                    /* Заполняем старое расположение робота землей*/
//                    PlayingField.setGround(this.xtemp, this.ytemp);
//                } else {
//                 /* Если робот выезжает за пределы экрана или на кучу золота или дыру, то
//             возвращаем старые координаты*/
//                    restoreCoordinate();
//                }
//            }
//        }
//  }


}
