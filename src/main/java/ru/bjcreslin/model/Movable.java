package ru.bjcreslin.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
Двигающиеся объекты такие как игрок, роботы хранят свои координаты в себе.
 */
public abstract class Movable {
    //координаты двигающегося объекта
    @Getter
    @Setter
    private Integer x;
    @Getter
    @Setter
    private Integer y;
    @Getter
    private MoveDispatcher moveDispatcher;


    public Movable(Integer x, Integer y) {
        this.moveDispatcher = new MoveDispatcher();
        this.x = x;
        this.y = y;
    }

    public abstract String getObjectSymbol();

    abstract public void action();

    @Setter
    private Integer xtemp;
    @Setter
    private Integer ytemp;

    public void saveCoord() {
        xtemp = this.getX();
        ytemp = this.getY();
    }

    public void restoreCoord() {
        setX(xtemp);
        setY(ytemp);
    }
}
