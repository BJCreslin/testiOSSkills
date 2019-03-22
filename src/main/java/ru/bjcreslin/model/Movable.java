package ru.bjcreslin.model;

import lombok.Getter;
import lombok.Setter;

/*
Двигающиеся объекты такие как игрок, роботы. хранят свои координаты в себе.
 */
public abstract class Movable {
    //координаты двигающегося объекта
    @Getter
    @Setter
    private Integer x;
    @Getter
    @Setter
    private Integer y;
    //Диспетчер движений
    @Getter
    private MoveDispatcher moveDispatcher;


    Movable(Integer x, Integer y) {
        this.moveDispatcher = MoveDispatcher.getInstance();
        this.x = x;
        this.y = y;
    }


    // Хранение и восстановление временных координат. (нужно для виртуальных ходов....)
    private Integer xtemp;
    private Integer ytemp;

    void saveCoord() {
        xtemp = this.getX();
        ytemp = this.getY();
    }

    void restoreCoord() {
        setX(xtemp);
        setY(ytemp);
    }

    public abstract String getObjectSymbol();

    abstract public void action();
}
