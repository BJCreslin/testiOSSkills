package ru.bjcreslin.model;

import lombok.Data;
import lombok.Getter;

/*
Двигающиеся объекты такие как игрок, роботы хранят свои координаты в себе.
 */
@Data
public abstract class Movable {
    //координаты двигающегося объекта
    private int x;
    private int y;
    private MoveDispatcher moveDispatcher;

    public Movable(int x, int y) {
        this.moveDispatcher = new MoveDispatcher();
        this.x = x;
        this.y = y;
    }

    abstract public void action();


}
